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
        for (Person person : personList) {
            personIList = new ArrayList<>();
            for (Person person1 : personList) {
                if (person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())) {
                    if (person != person1) {
                        personIList.add(person1);
                    }
                }
            }
            map.put(person, personIList);
            //Testing the first person in the list friends
            /*
            System.out.println("Person: " + person.getFirstName());
            for(int i=0 ;i <=personIList.size()-1 ; i++){
               System.out.println("List of friends of "+  person.getFirstName() +" Friends: " + personIList.get(i).getFirstName());
            }
            System.out.println("Map is " + map);
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }
    }



}