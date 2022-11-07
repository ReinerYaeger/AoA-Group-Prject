import java.io.File;
import java.io.FileNotFoundException;

public class Controller {

    Controller() {

    }

    public boolean isFileFound(String fileLocation) {
        System.out.println("Analysing file: " + fileLocation);

        try {
            File file = new File(fileLocation);
            if (! file.exists()) {
                throw new FileNotFoundException("File not found");
            }
            if(!file.canRead()){
                throw new FileNotFoundException("File cannot be read");
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
