package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph{

    List<Person> personList = new ArrayList<>();
    List<Activity> activityList = new ArrayList<>();
    Map<Person,Relation> map = new HashMap<>();
    public Graph(List<Person> p, List<Activity> a){
       personList = p;
       activityList = a;

               /* if(person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())){
                    map.put(person,person1);
                }
                }
            }
        }*/
        createGraph();
    }

    public void createGraph() {

        for (Person person : personList) {
            //System.out.println(person);
            for (Person person1 : personList) {
                if (person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())) {
                    map.put(person, person.getRelation());
                }
            }
        }

        System.out.println(map.toString());
    }


}