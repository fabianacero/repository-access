package repositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoRepo implements Repository {

    private static final int DEFAULT_PORT = 27017;
    private MongoDatabase database;

    public Boolean connect(String db, String user, String pass) {

        int collections = 0;
        // Creating mongo client
        MongoClient mongo = new MongoClient("localhost", DEFAULT_PORT);

        // Creating credentials
        MongoCredential credentials = MongoCredential.createCredential(user, db, pass.toCharArray());

        // Accesing Data
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

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }
}
