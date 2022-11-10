package Controller;

import Model.Activity;
import Model.Graph;
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
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Person person;
    Activity activity;
    Graph graph;
    View view;

    public Controller( ) {
        person = new Person();
        activity = new Activity();
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
        graph = new Graph(loadPersons(filePersonLocation),loadActivities(fileActLocation2));
    }

    public List<Person> getPersonList(){

        return null;
    }

    public List<Person> loadPersons(String filePersonLocation) {

        List<Person> pl = new ArrayList<Person>();

        try(Reader reader = Files.newBufferedReader(( Paths.get(filePersonLocation)))){
            Iterable<CSVRecord> persons = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord person : persons) {
                String firstName = person.get(0);
                String lastName = person.get(1);
                String phoneNumber = person.get(2);
                String emailAddress = person.get(3);
                String resCom = person.get(4);
                String school = person.get(5);
                String employer = person.get(6);
                boolean reqPrivacy = Boolean.parseBoolean(person.get(7));
                pl.add(new Person(firstName, lastName, phoneNumber, emailAddress, resCom, school, employer,reqPrivacy));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pl;
    }

    public List<Activity> loadActivities(String fileActLocation2) {

        List <Activity>al = new ArrayList<Activity>();
        try (Reader reader = Files.newBufferedReader(( Paths.get(fileActLocation2) ))) {

            Iterable<CSVRecord> persons = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord person : persons) {
                String firstName = person.get(0);
                String lastName = person.get(1);
                String activity = person.get(2);

                System.out.println("Record No - " + person.getRecordNumber()
                                           + " : " + firstName + " " + lastName + " " +
                                           activity);

                al.add(new Activity(firstName,lastName,activity));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return al;
    }
}
