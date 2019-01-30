package mappers;

import java.util.Objects;

public class Config {
    private int id;
    private String name;
    private Credentials credentials;
    private String database;
    private String host;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return id == config.id &&
                Objects.equals(name, config.name) &&
                Objects.equals(credentials, config.credentials) &&
                Objects.equals(database, config.database) &&
                Objects.equals(host, config.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credentials, database, host);
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credentials=" + credentials +
                ", database='" + database + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
