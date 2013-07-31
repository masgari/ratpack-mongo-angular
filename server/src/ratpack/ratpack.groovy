import org.ratpackframework.groovy.templating.TemplateRenderer

import static org.ratpackframework.groovy.RatpackScript.ratpack

import com.mongodb.MongoClient
import com.mongodb.DB
import com.mongodb.DBCollection

def indexPages = ["index.html"] as String[]

MongoClient mongo = new MongoClient('ds037468.mongolab.com', 37468)
DB db = mongo.getDB('ratpack')
boolean auth = db.authenticate('ratpack', 'ratpack'.toCharArray())

ratpack {
    handlers {
 
      get("collections") { 
        response.send "Available database collections are ${ db.getCollectionNames().join(', ') }"
      }
  
      assets "public", indexPages
    }
}