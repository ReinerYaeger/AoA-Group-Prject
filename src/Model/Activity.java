/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*/

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

    public Activity(){
        activityName = "";
        firstName = "";
        lastName = "";
    }

    public Activity(String firstName, String lastName,String activityName ) {
        this.activityName = activityName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getActivityName( ) {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    @Override
    public String toString( ) {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;


    }
}
