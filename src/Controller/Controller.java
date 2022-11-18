package Controller;

import Model.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    Person person;
    Activity activity;

    Relation relationship;
    View view;
    HashMap<Integer ,String> map;

    public Controller( ) {
        person = new Person();
        activity = new Activity();
        relationship = new Relation();
        map = new HashMap<>();
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

        //Graph graph = new Graph(loadPersons(filePersonLocation), loadActivities(fileActLocation2));
        long startTime = System.nanoTime();

        GGraph graph = new GGraph(loadPersons(filePersonLocation));
        graph.findFriends();
        ArrayList<Person> randomPeople = graph.findRandomPeople();

        System.out.println("by " + graph.degreeOfSeparationRandom(randomPeople.get(0),randomPeople.get(1)));

        graph.associateActivities(loadActivities(fileActLocation2));
        graph.recommendationEngine();


        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

    }

    public List<Person> loadPersons(String filePersonLocation) {

        List<Person> pl = new ArrayList<Person>();
         Person p;

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
                p = new Person(firstName, lastName, phoneNumber, emailAddress,reqPrivacy);
                relationship = new Relation(resCom,school,employer);
                pl.add(new Person(firstName, lastName, phoneNumber, emailAddress,reqPrivacy,relationship));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(map.toString());
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

                al.add(new Activity(firstName,lastName,activity));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return al;
    }
}
