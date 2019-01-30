package repositories;

public interface Repository {
    boolean connect(String db, String user, String pass, String host);
}
