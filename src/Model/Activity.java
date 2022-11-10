package Model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.accessibility.AccessibleIcon;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Activity {
    String activityName;

    String firstName;
    String lastName;
    Person srcPerson;
    Person destPerson;

    public Activity(){
        activityName = "";
        firstName = "";
        lastName = "";
        srcPerson = new Person();
        destPerson = new Person() ;
    }

    public Activity(String firstname, String lastName, String activityName){

    }

    public Activity(String activityName, Person srcPerson, Person destPerson) {
        this.activityName = activityName;
        /*this.srcPerson = srcPerson;
        this.destPerson = destPerson;*/
    }

    public Activity(String activityName) {
        this.activityName = activityName;
    }

    public Person getSrcPerson( ) {
        return srcPerson;
    }

    public void setSrcPerson(Person srcPerson) {
        this.srcPerson = srcPerson;
    }

    public Person getDestPerson( ) {
        return destPerson;
    }

    public void setDestPerson(Person destPerson) {
        this.destPerson = destPerson;
    }

    @Override
    public String toString( ) {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getActivityName( ) {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
