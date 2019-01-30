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

            List<Config> configs = null;
            try {
                configs = map.readValue(configFile.getFileReader(), new TypeReference<List<Config>>() {
                });
            } catch (IOException e) {
                System.err.println("Failed to deserialize JSON: " + e.getMessage());
                System.exit(-1);
            }

            for (Config conf : configs) {

                if (!conf.isEnabled()) {
                    continue;
                }

                switch (conf.getName()) {
                    case DB_MONGO:
                        connectToMongo(conf);
                        break;
                    case DB_REDIS:
                        connectToRedis(conf);
                        break;
                }
            }
        }
    }

    /**
     * Connects to mongodb repository
     */
    private static void connectToMongo(Config config) {

        Credentials credentials = config.getCredentials();

        MongoRepo db = new MongoRepo();

        if (!db.connect(config.getDatabase(), credentials.getUser(), credentials.getPass(), config.getHost())) {
            throw new MongoClientException("No fue posible conectarse a la base de datos");
        }

        MongoDatabase mongoDb = db.getDatabase();
        MongoCollection<Document> collection = mongoDb.getCollection("customers");

        for (Document doc : collection.find()) {
            System.out.println(doc.get("name"));
        }
    }

    /**
     * Connects to redis repository
     */
    private static void connectToRedis(Config config) {
        Jedis redisDb = new Jedis(config.getHost());
        redisDb.auth(config.getCredentials().getPass());
        redisDb.set("database", config.getDatabase());
        redisDb.set("timestamp", String.valueOf(System.currentTimeMillis()));
        redisDb.set("caller-class", RepoAdmin.class.toString());

        for (String element : redisDb.keys("*")) {
            System.out.println(element + " -> " + redisDb.get(element));
        }
    }
}
