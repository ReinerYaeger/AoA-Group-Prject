package Model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Person {
    String firstName;
    String lastName;
    String phoneNumber;
    String emailAddress;
    String resCom ; // residential community
    String school;
    String employer;
    boolean reqPrivacy;// request privacy

    //default constructor
    public Person() {
        firstName = "";
        lastName = "";
        phoneNumber = "";
        emailAddress = "";
        resCom = "";
        school = "";
        employer = "";
        reqPrivacy = false;
    }
    public Person(String firstName, String lastName, String phoneNumber, String emailAddress, String resCom, String school, String employer, boolean reqPrivacy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.resCom = resCom;
        this.school = school;
        this.employer = employer;
        this.reqPrivacy = reqPrivacy;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber( ) {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress( ) {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getResCom( ) {
        return resCom;
    }

    public void setResCom(String resCom) {
        this.resCom = resCom;
    }

    public String getSchool( ) {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmployer( ) {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public boolean isReqPrivacy( ) {
        return reqPrivacy;
    }

    public void setReqPrivacy(boolean reqPrivacy) {
        this.reqPrivacy = reqPrivacy;
    }

    public void  loadPersons(String filePersonLocation) {

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
                String reqPrivacy = person.get(7);
                //Store add the graph
                System.out.println("Record No - " + person.getRecordNumber()
                                           + " : " + firstName + " " + lastName + " " +
                                           phoneNumber + " " + emailAddress + " " + resCom + " " +
                                           school + " " + employer + " " + reqPrivacy);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadActivities(String fileActLocation2){
        try(Reader reader = Files.newBufferedReader(( Paths.get(fileActLocation2)))){

            Iterable<CSVRecord> persons = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord person : persons) {
               String firstName = person.get(0);
               String lastName = person.get(1);
               String activity = person.get(2);

               System.out.println("Record No - " + person.getRecordNumber()
                                           + " : " + firstName + " " + lastName + " " +
                                           activity);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String toString( ) {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", resCom='" + resCom + '\'' +
                ", school='" + school + '\'' +
                ", employer='" + employer + '\'' +
                ", reqPrivacy=" + reqPrivacy +
                '}';
    }
}
