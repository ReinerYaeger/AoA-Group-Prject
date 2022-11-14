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
    boolean reqPrivacy;// request privacy
    Relation relation;

    //default constructor
    public Person() {
        firstName = "";
        lastName = "";
        phoneNumber = "";
        emailAddress = "";
        reqPrivacy = false;
        relation = new Relation();
    }
    public Person(String firstName, String lastName, String phoneNumber, String emailAddress, boolean reqPrivacy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.reqPrivacy = reqPrivacy;
    }

    public Person(String firstName, String lastName, String phoneNumber, String emailAddress, boolean reqPrivacy,
    Relation relation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.reqPrivacy = reqPrivacy;
        this.relation = relation;

    }

    public Person(Person person){
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.phoneNumber = person.phoneNumber;
        this.emailAddress = person.emailAddress;
        this.reqPrivacy = person.reqPrivacy;
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

    public boolean isReqPrivacy( ) {
        return reqPrivacy;
    }

    public void setReqPrivacy(boolean reqPrivacy) {
        this.reqPrivacy = reqPrivacy;
    }

    public Relation getRelation( ) {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }
}
