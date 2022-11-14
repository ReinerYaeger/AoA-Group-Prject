package Model;

public class Relation {
    String resCom ; // residential community
    String school;
    String employer;


    public Relation(){
        resCom = "";
        school ="";
        employer ="";
    }

    public Relation(String resCom, String school, String employer) {
        this.resCom = resCom;
        this.school = school;
        this.employer = employer;
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

    @Override
    public String toString( ) {
        return "Relation{" +
                "resCom='" + resCom + '\'' +
                ", school='" + school + '\'' +
                ", employer='" + employer + '\'' +
                '}';
    }
}
