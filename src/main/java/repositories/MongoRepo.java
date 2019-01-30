package repositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoRepo implements Repository {

    private static final int DEFAULT_PORT = 27017;
    private MongoDatabase database;

    public boolean connect(String db, String user, String pass, String host){

        int collections = 0;
        // Creating mongo client
        MongoCredential mongoCredentials = MongoCredential.createCredential(user, db, pass.toCharArray());
        MongoClientOptions mongoOptions = new MongoClientOptions.Builder().build();
        MongoClient mongo = new MongoClient(new ServerAddress(host, DEFAULT_PORT), mongoCredentials, mongoOptions);

        // Accessing Data
        database = mongo.getDatabase(db);
        ListDatabasesIterable<Document> databases = mongo.listDatabases();

        for (Document doc : databases) {
            collections++;
        }

        return collections > 0;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
