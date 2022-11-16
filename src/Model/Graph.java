package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph{

    class Edge{
        Person src, dest;
    }
    int noVertices, noEdges;
    List<Edge> edges;
    List<List<Edge>> edgesList;

    List<Person> personList = new ArrayList<>();
    List<Activity> activityList = new ArrayList<>();
    Map<Person,List<Edge>> map = new HashMap<>();
    public Graph(List<Person> p, List<Activity> a){
       personList = p;
       activityList = a;

        createGraph();
    }

    public void createGraph() {
        int i = 0;
        for (Person person : personList) {
            if (i == 10)
                break;

            for (Person person1 : personList) {
                List<Person> personIList = new ArrayList<>();
                if (person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())) {

                    if (person != person1) {

                    }
                }
            }
            edgesList.add(edges);
            i++;
        }

    }
}