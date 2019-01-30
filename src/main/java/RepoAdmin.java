import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClientException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import mappers.Config;
import mappers.Credentials;
import org.bson.Document;
import redis.clients.jedis.Jedis;
import repositories.MongoRepo;

import java.io.IOException;
import java.util.List;


public class RepoAdmin {

    private static final String DB_MONGO = "mongodb";
    private static final String DB_REDIS = "redis";

    public static void main(String[] args) {
        FileReader configFile = new FileReader();
        if (configFile.readFile("src/main/resources/config.json")) {

            ObjectMapper map = new ObjectMapper();
            map.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

            try {
                List<Config> configs = map.readValue(configFile.getFileReader(), new TypeReference<List<Config>>() {
                });

                for (Config conf : configs) {
                    switch (conf.getName()) {
                        case DB_MONGO:
                            connectToMongo(conf);
                            break;
                        case DB_REDIS:
                            connectToRedis(conf);
                            break;
                    }
                }

            } catch (MongoClientException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Connects to mongodb repository
     *
     * @param config
     */
    private static void connectToMongo(Config config) {

        Credentials credentials = config.getCredentials();

        MongoRepo db = new MongoRepo();

        if (!db.connect(config.getDatabase(), credentials.getUser(), credentials.getPass())) {
            throw new MongoClientException("No fue posible conectarse a la base de datos");
        }

        MongoDatabase dbInstance = db.getDatabase();
        MongoCollection<Document> collection = dbInstance.getCollection("customers");

        for (Document doc : collection.find()) {
            System.out.println(doc.get("name"));
        }
    }

    /**
     * Connects to redis repository
     *
     * @param config
     */
    private static void connectToRedis(Config config) {
        System.out.println("Connecting to Redis");
        Jedis db = new Jedis(config.getHost());
        System.out.println(db.ping());
    }
}
