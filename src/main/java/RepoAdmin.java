import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import mappers.Config;
import mappers.Credentials;
import repositories.MongoRepo;

import java.io.IOException;

public class RepoAdmin {

    public static void main(String[] args) {
        FileReader configFile = new FileReader();
        if (configFile.readFile("src/main/resources/config.json")){
            ObjectMapper map = new ObjectMapper();
            map.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            try {
                Config config = map.readValue(configFile.getFileReader(), Config.class);
                Credentials credentials = config.getCredentials();

                MongoRepo db = new MongoRepo();
                db.connect("sample", credentials.getUser(), credentials.getPass());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
