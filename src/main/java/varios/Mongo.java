package varios;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Mongo {
	public static void main(String[] Args) {

		Mongo mn = new Mongo();
		mn.mongo();
	}

	void mongo() {
		try {

			MongoClientURI uri = new MongoClientURI("mongodb://gbi:gbi360@localhost:10050");
			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase db = mongoClient.getDatabase("quilotoadb");
			MongoCollection collection = db.getCollection("invoice_sri");

			String id = "00";

//			FindIterable<Document> fi = collection.find(Filters.and(Filters.eq("jsoninvoice.factura.infoTributaria.ruc", id)));
//			
//
//			MongoCursor<Document> cursor = fi.iterator();
//			System.out.println(cursor.);
//
//			try {
//
//				while (cursor.hasNext()) {
//					System.out.println(cursor.next().toJson());
//
//				}
//
//			} finally {
//
//				cursor.close();
//
//			}
			
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", "0992632291001");
			DBCursor cursor = (DBCursor) collection.find(searchQuery);
			 
			while (cursor.hasNext()) {
			    System.out.println(cursor.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

}
