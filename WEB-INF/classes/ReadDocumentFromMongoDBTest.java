import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.DBCursor;

public class ReadDocumentFromMongoDBTest{
	public static void main(String[] args) {
		try{
			MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
			System.out.println("Connected to server...");
			DB db = mongoClient.getDB("test");
			System.out.println("Connected to database...");
			//MongoCollection<Document> collection = mongoDatabase.getCollection("test");\
			DBCollection call = db.getCollection("test");
			DBCursor cursor = call.find();
			System.out.println("Connected to collection...");

			while(cursor.hasNext()){
				int i = 1;
				System.out.println(cursor.next());
				i++;
			}
		
		}catch(Exception e){

		}

	}
}