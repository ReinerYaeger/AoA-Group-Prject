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
