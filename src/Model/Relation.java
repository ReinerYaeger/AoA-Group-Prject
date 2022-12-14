/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*
* // Date Created: 2021-03-29
* Title: Aoa Group Project
*/
package Model;

public class  Relation {
    String resCom ; // residential community
    String school;
    String employer;
    int distance;
    boolean visited;


    public Relation(){
        resCom = "";
        school ="";
        employer ="";
            int distance=0;
            visited = false;
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

    public int getDistance( ) {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isVisited( ) {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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
