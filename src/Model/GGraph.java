package Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GGraph {

    Map<Person, List<Person>> map = new HashMap<>();
    List<Person> personList = new ArrayList<>();
    Person person = new Person();

    public GGraph(List<Person> pl) {
        personList = pl;
    }

    public void findFriends(){

        List<Person> personIList = new ArrayList<>();
        int l=0;
        for (Person person : personList) { // O(n)
            personIList = new ArrayList<>();
            if(l==10)
                break;
            for (Person person1 : personList) { // O(n) n*n = O(n^2)
                if (person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())) {
                    if (person != person1) {
                        personIList.add(person1);
                    }
                }
            }
            map.put(person, personIList);
            l++;

            //Testing the first person in the list friends
            System.out.println("Person: " + person.getFirstName());
            for(int i=0 ;i <=personIList.size()-1 ; i++){
               System.out.println("List of friends of "+  person.getFirstName() +" Friends: " + personIList.get(i).getFirstName());
            }
            System.out.println("Map is " + map);
        }
    }

    //implement floyd warshall algorithm on the graph
    public void findShortestPath(){
        //initilize the matrix to zero
        long [][] matrix = new long [personList.size()][personList.size()];
        for (long i = 0; i < personList.size(); i++) {
            for (long j = 0; j < personList.size(); j++) {
                matrix[(int) i][(int) j] = 0;
            }
        }

        //fill the matrix with the values from the map
        for (long i = 0; i < personList.size(); i++) {
            for (long j = 0; j < personList.size(); j++) {
                if (map.get(personList.get((int) i)).contains(personList.get((int) j))) {
                    matrix[(int) i][(int) j] = 1;
                }
            }
        }

        for (int i = 0; i < personList.size(); i++) {
            for (int j = 0; j < personList.size(); j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        for (long k = 0; k < personList.size(); k++) {
            for (long i = 0; i < personList.size(); i++) {
                for (long j = 0; j < personList.size(); j++) {
                    if (matrix[(int) i][(int) k] == 1 && matrix[(int) k][(int) j] == 1) {
                        matrix[(int) i][(int) j] = 1;
                        System.out.print(matrix[(int) i][(int) j] + " ");
                    }
                }
            }
        }
        System.out.println("After Floyd Warshall");
        for (int i = 0; i < personList.size(); i++) {
            for (int j = 0; j < personList.size(); j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }



}