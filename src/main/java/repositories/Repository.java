package repositories;

public interface Repository {
    Boolean connect(String db, String user, String pass);
}
