/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*/
package Model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Person{
    String firstName;
    String lastName;
    String phoneNumber;
    String emailAddress;
    boolean reqPrivacy;// request privacy
    Relation relation;
    List<Activity> activity , recommendedActivity;

    //default constructor
    public Person() {
        firstName = "";
        lastName = "";
        phoneNumber = "";
        emailAddress = "";
        reqPrivacy = false;
        relation = new Relation();
        activity = new ArrayList<>();
        recommendedActivity = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String phoneNumber, String emailAddress, boolean reqPrivacy, Relation relation, ArrayList<Activity> activity,ArrayList<Activity> r) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.reqPrivacy = reqPrivacy;
        this.relation = relation;
        this.activity = activity;
        recommendedActivity = r;
    }

    public Person(String firstName, String lastName, String phoneNumber, String emailAddress, boolean reqPrivacy) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.reqPrivacy = reqPrivacy;
        activity = new ArrayList<>();
        recommendedActivity = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String phoneNumber, String emailAddress, boolean reqPrivacy, Relation relationship) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.reqPrivacy = reqPrivacy;
        this.relation = relationship;
        activity = new ArrayList<>();
        recommendedActivity = new ArrayList<>();
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

    public List<Activity> getActivity( ) {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }

    public void appendActivity(Activity activity) {
        this.activity.add(activity);
    }

    public void appendActivity(String activity) {
        Activity a = new Activity();
        a.setActivityName(activity);
        this.activity.add(a);
    }


    public List<Activity> getRecommendedActivity( ) {
        return recommendedActivity;
    }

    public void setRecommendedActivity(List<Activity> recommendedActivity) {
        this.recommendedActivity = recommendedActivity;
    }

    public void appendRecommendedActivity(Activity activity) {
        this.recommendedActivity.add(activity);
    }


    public void appendRecommendedActivity(String activity) {
        Activity a = new Activity();
        a.setActivityName(activity);
        this.recommendedActivity.add(a);
    }

    public String printActivity() {
        StringBuilder str = new StringBuilder();
        for (Activity a : activity) {
            str.append(a.getActivityName()).append(" \n\t");

        }
        return str.toString();
    }

    public String printRecommendedActivity() {
        StringBuilder str = new StringBuilder();
        for (Activity a : recommendedActivity) {
            str.append(a.getActivityName()).append(" \n\t");
        }
        return str.toString();
    }

    /*Removing Duplicate Entries from the activity lists*/
    public void removeDuplicates() {
        //Remove duplicate string from activity list and recommended activity list
        activity = activity.stream().distinct().collect(Collectors.toList());
        recommendedActivity = recommendedActivity.stream().distinct().collect(Collectors.toList());

    }


    public boolean isRelatedTo(Person p) {
        if(p.getRelation().getEmployer().equals(this.relation.getEmployer()))
            return true;
        if(p.getRelation().getSchool().equals(this.relation.getSchool()))
            return true;
        if(p.getRelation().getResCom().equals(this.relation.getResCom()))
            return true;
        return false;
    }

    @Override
    public String toString( ) {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", reqPrivacy=" + reqPrivacy +
                ", relation=" + relation +
                ", activity=" + activity +
                '}';
    }
}
