import static org.ratpackframework.groovy.RatpackScript.ratpack

import com.mongodb.MongoClient
import com.mongodb.DB
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.util.JSON
import org.bson.types.ObjectId
import com.mongodb.gridfs.GridFSInputFile
import com.mongodb.gridfs.GridFSDBFile
import com.mongodb.gridfs.GridFS
import sun.misc.BASE64Decoder
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufOutputStream
import java.util.UUID
import io.netty.buffer.Unpooled
import org.apache.commons.io.IOUtils

def indexPages = ["index.html"] as String[]

MongoClient mongo = new MongoClient('ds037468.mongolab.com', 37468)
DB db = mongo.getDB('ratpack')
db.authenticate('ratpack', 'ratpack'.toCharArray())
DBCollection personCollection = db.getCollection("person")
GridFS imageStore = new GridFS(db, 'images')
BASE64Decoder decoder = new BASE64Decoder()

ratpack {
    handlers {
        // validation method to make sure we can connect to our DB
        get("api/collections") {
            response.send "Available database collections are ${db.getCollectionNames().join(', ')}"
        }

        // list of people
        get("api/person") {
            def cursor = personCollection.find(new BasicDBObject(), new BasicDBObject("name", 1).append("image", 1))
            def items = []
            try {
                while (cursor.hasNext()) {
                    items << JSON.serialize(cursor.next())
                }
            } finally {
                cursor.close()
            }
            response.send 'application/json', "[${items.join(',')}]"
        }

        // create a person
        post("api/person") {
            accepts.type("application/json") {
                personCollection.insert(JSON.parse(request.text) as DBObject)
                response.send()
            }.send()
        }

        // get details
        get("api/person/:id") {
            def person = personCollection.findOne(new BasicDBObject("_id", new ObjectId(pathTokens.id)))
            if (person) {
                response.send 'application/json', JSON.serialize(person)
            } else {
                clientError 404
            }
        }

        // save a base64 encoded image via gridfs
        post("api/image") {
          def image = decoder.decodeBuffer(request.text as String)
          String filename = UUID.randomUUID()
          GridFSInputFile file = imageStore.createFile(image)
          file.setFilename(filename)
          file.setContentType("image/png")
          file.save()
          response.send 'application/json', "{'filename':'${filename}'}"
        }

        // render the image in ratpack
        get("api/image/:filename") {
          GridFSDBFile image = imageStore.findOne(pathTokens.filename)
          if( image ){
            byte[] imageBytes = IOUtils.toByteArray(image.inputStream)
            response.send 'image/png', Unpooled.wrappedBuffer(imageBytes)            
          } else {
            clientError 404
          }
       }

       assets "public", indexPages
    }
}