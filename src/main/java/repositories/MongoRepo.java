package repositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public class MongoRepo implements Repository {

    private static final int DEFAULT_PORT = 27017;

    public Boolean connect(String db, String user, String pass){

        // Creating mongo client
        MongoClient mongo = new MongoClient("localhost", DEFAULT_PORT);

        // Creating credentials
        MongoCredential credentials;
        credentials = MongoCredential.createCredential(user, db, pass.toCharArray());

        // Accesing Data
        MongoDatabase database = mongo.getDatabase(db);
        System.out.println(credentials);

        return true;
    }
}
