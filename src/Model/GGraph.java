package Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GGraph {

    Map<Person, List<Person>> map = new HashMap<>();
    List<Person> personList = new ArrayList<>();
    Person person = new Person();

    int [][] distanceMatrix;

    final int SIZE = 10;
    public GGraph(List<Person> pl) {
        personList = pl;
    }

    public void findFriends(){

        List<Person> personIList = new ArrayList<>();
        int l=0,i=0;
        for (Person person : personList) { // O(n)
            personIList = new ArrayList<>();
            if(l==SIZE)
                break;
            for (Person person1 : personList) { // O(n) n*n = O(n^2)
                if (person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())) {
                    if (person != person1) {
                        personIList.add(person1);
                    }
                    if(i==SIZE)
                        break;
                    i++;
                }
            }
            map.put(person, personIList);
            l++;

            //Testing the first person in the list friends
            System.out.println("Person: " + person.getFirstName());
            for(i=0 ;i <= SIZE-1 ; i++){
               System.out.println("List of friends of "+  person.getFirstName() +" Friends: " + personIList.get(i).getFirstName());
            }
        }
    }

    //implement floyd warshall algorithm on the Hashmap
    public void findDegreeOfSeparation(){

        for(int k=0 ; k <=SIZE-1 ; k++){
            for(int i=0 ; i <=SIZE-1 ; i++){
                for(int j=0 ; j <=SIZE-1; j++){
                    System.out.println("k: " + k + " i: " + i + " j: " + j);

                }
            }
        }

    }
}