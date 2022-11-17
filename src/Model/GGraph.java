package Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GGraph {

    Map<Person, Map<Integer,Person>> map = new HashMap<Person, Map<Integer,Person>>();
    List<Person> personList = new ArrayList<>();

    Map<Integer,Person> edgeMap = new HashMap<Integer,Person>();
    int distance = 0;
    final int SIZE = personList.size();

    Person person = new Person();

    public GGraph(List<Person> pl) {
        personList = pl;
    }

    public void findFriends(){

        List<Person> personIList = new ArrayList<>();
        int l=0,i=0;

        for (Person person : personList) { // O(n)
            distance = 1;
            personIList = new ArrayList<>();
            edgeMap = new HashMap<>();
            if(l==SIZE)
                break;

            for (Person person1 : personList) { // O(n) n*n = O(n^2)

                if ((person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool()))
                        && person != person1 ) {
                        person1.getRelation().setDistance(distance);
                        edgeMap.put(distance,person1);
                        personIList.add(person1);
                        distance = 0;

                        if(i == SIZE)
                            break;
                    i++;
                }
                distance++;
            }
            map.put(person, edgeMap);
            l++;

            //Testing the first person in the list friends
            System.out.println("Person: " + person.getFirstName() + " " + person.getLastName() + " " + map.get(person).get(i));

            for(i = 0 ;i <=SIZE  ; i++){
                if(person != personList.get(i) && edgeMap.containsKey(i)){
                    System.out.println("\t" + personList.get(i).getFirstName()+ " " +personList.get(i).getLastName() + " Distance: " + personIList.get(i).getRelation().getDistance());
                }
            }
        }
    }

    public void depthFirstSearch(){
        for( int i =0 ; i<SIZE ; i++){
           // Person dfsPerson = map.get(personList.get(i)).;
        }

    }

    //implement floyd warshall algorithm on the graph
    public void findShortestPath(){

    }



}