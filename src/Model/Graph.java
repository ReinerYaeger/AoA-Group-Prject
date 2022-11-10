package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Model.*;

public class Graph{

    private List<List<Person>> adjacencyMatrix;
    private Person person;
    private int nov; //number of vertices
    private int noe; //number of edges
    private String vertexLabel[];
    private int[] distance;
    private boolean[] visited;

    public Graph(List<Person> personList, List<Activity> activityList){
        List<List<Person>> adjList = new ArrayList<>();

        for(int i = 0; i < personList.size(); i++){
            adjList.add(i, new ArrayList<>());
        }
        for(int i = 0; i < activityList.size(); i++){
            for(int j = 0; j < activityList.size(); j++)
            if(personList.get(i).getFirstName().equals(activityList.get(j).firstName) &&
            personList.get(i).getLastName().equals(activityList.get(j).lastName)){

            }
        }



    }
}