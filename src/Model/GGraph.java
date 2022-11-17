/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*/
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GGraph {

    Map<Person, Map<Person,Integer>> map = new HashMap<Person, Map<Person,Integer>>();
    List<Person> personList = new ArrayList<>();

    Map<Person,Integer> edgeMap = new HashMap<Person,Integer>();
    int distance = 0;
    final int SIZE = 10;

    Person person = new Person();

    public GGraph(List<Person> pl) {
        personList = pl;
    }

    public void findFriends(){

        List<Person> personIList = new ArrayList<>();
        int l=0,i=0;

        for (Person person : personList) { // O(n)
            distance = 0;
            personIList = new ArrayList<>();
            edgeMap = new HashMap<>();
            if(l==SIZE)
                break;

            for (Person person1 : personList) { // O(n) n*n = O(n^2)
                distance++;
                if ((person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool()))
                        && person != person1 ) {
                        person1.getRelation().setDistance(distance);
                        edgeMap.put(person1,distance);
                        personIList.add(person1);
                        distance = 0;

                        if(i == SIZE)
                            break;
                    i++;
                }
            }
            map.put(person, edgeMap);
            l++;

            //Testing the first person in the list friends
            System.out.println("Person: " + person.getFirstName());

            for(i = 0 ;i <=SIZE  ; i++){
                if(person != personList.get(i) && edgeMap.containsKey(personList.get(i))){
                    System.out.println("\t" + personList.get(i).getFirstName()+ " " +personList.get(i).getLastName() + " Distance: " + personIList.get(i).getRelation().getDistance());
                }
            }
           /* for(i=0 ;i <=SIZE ; i++){
                if(map.get(edgeMap)) {
                    System.out.println("friends of " + person.getFirstName() +
                                               "\n Friends: " + personList.get(i).getFirstName() +
                                               "\n Distance: " + edgeMap.get(personList.get(i)));
                }
            }*/
    }
    }

    //implement floyd warshall algorithm on the graph
    public void findShortestPath(){

    }



}