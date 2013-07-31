import static org.ratpackframework.groovy.RatpackScript.ratpack

import com.mongodb.MongoClient
import com.mongodb.DB
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.util.JSON
import org.bson.types.ObjectId

def indexPages = ["index.html"] as String[]

MongoClient mongo = new MongoClient('ds037468.mongolab.com', 37468)
DB db = mongo.getDB('ratpack')
db.authenticate('ratpack', 'ratpack'.toCharArray())
DBCollection personCollection = db.getCollection("person")

ratpack {
    handlers {
        // validation method to make sure we can connect to our DB
        get("collections") {
            response.send "Available database collections are ${db.getCollectionNames().join(', ')}"
        }

        // list of people
        get("api/person/") {
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

        assets "public", indexPages
    }
}