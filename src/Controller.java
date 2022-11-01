import java.io.File;
import java.io.FileNotFoundException;

public class Controller {

    Controller() {
        System.out.println("Controller created");
    }

    public boolean isFileFound(String fileLocation) {
        System.out.println("Analysing file: " + fileLocation);

        try {
            File file = new File(fileLocation);
            if (! file.exists()) {
                throw new FileNotFoundException("File not found");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void analyseFile(String fileLocation) {
        System.out.println("Analysing file: " + fileLocation);

    }
}
