package Controller;

import Model.Person;
import View.View;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Controller {
    Person person;
    View view;

    public Controller( ) {
        person = new Person();
    }

    public boolean isFileFound(String filePersonLocation, String fileActLocation2) {

        try {
            File file = new File(filePersonLocation);
            File file2 = new File(fileActLocation2);
            if (! file.exists() || ! file2.exists()) {
                JOptionPane.showMessageDialog(null, "File not found Try Again\n" + filePersonLocation ,
                                              "Error", JOptionPane.ERROR_MESSAGE);
                throw new FileNotFoundException("File not found");
            }
            if(!file.canRead() || !file2.canRead()) {
                JOptionPane.showMessageDialog(null, "File cannot be read\n" + fileActLocation2,
                                              "Error", JOptionPane.ERROR_MESSAGE);
                throw new FileNotFoundException("File cannot be read");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void readFiles(String filePersonLocation, String fileActLocation2) {
        person.loadPersons(filePersonLocation);
        person.loadActivities(fileActLocation2);
    }

    public List<Person> getPersonList(){

        return null;
    }
}
