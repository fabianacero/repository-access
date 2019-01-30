import java.io.File;

public class FileReader {

    File fileReader;

    /**
     * Inicializa el onjeto de archivo
     *
     * @param path
     * @return
     */
    public Boolean readFile(String path) {
        fileReader = new File(path);
        return fileReader.exists() && fileReader.isFile();
    }

    /**
     * Obtiene el objeto cde archivo
     *
     * @return
     */
    public File getFileReader() {
        return fileReader;
    }
}
