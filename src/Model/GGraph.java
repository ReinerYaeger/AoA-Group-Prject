package Model;


import java.util.*;

public class GGraph {

    static Map<Person, List<Person>> map = new HashMap<>();
    static List<Person> personList = new ArrayList<>();
    final int SIZE = 100;
    List<Person> personIList = new ArrayList<>();
    Person person = new Person();

    public GGraph(List<Person> pl) {
        personList = pl;

        //pl.sort(Comparator.comparing(Person::getFirstName));
    }

    public void findFriends( ){

        int l=0,i=0;
        for (Person person : personList) { // O(n)
            if(l==SIZE)
                break;
            personIList = new ArrayList<>();
            for (Person person1 : personList) { // O(n) n*n = O(n^2)
                if (person.getRelation().getEmployer().equals(person1.getRelation().getEmployer())
                        || person.getRelation().getResCom().equals(person1.getRelation().getResCom())
                        || person.getRelation().getSchool().equals(person1.getRelation().getSchool())) {
                    if (person != person1) {
                        personIList.add(person1);
                    }
                    if(i==SIZE)
                        break;
                }
            }
            map.put(person, personIList);
            l++;
        }

    //Checking the friends of each person in the list

        for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
            System.out.println(entry.getKey().getFirstName() + " " + entry.getKey().getLastName());
            for (i = 0; i <= map.size(); i++) {
                System.out.println("\tFriends: " + map.get(entry.getKey()).get(i).getFirstName());
            }
        }
    }

    public ArrayList<Person> findRandomPeople(){

        Person person1 = new Person(),person2 = new Person();
        int random1 = (int) ( Math.random() * map.size() );
        int random2 = (int) ( Math.random() * map.size() );

        if(random1==random2)
            random2 = (int) ( Math.random() * map.size() );

        int counter = 0;
        for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
            System.out.println(entry.getKey().getFirstName() + " " + entry.getKey().getLastName());

            if(random1 == counter)
                person1 = entry.getKey();
            if(random2 == counter )
                person2 = entry.getKey();

            for (int i = 0; i <= map.size(); i++) {
                System.out.println("\tFriends: " + map.get(entry.getKey()).get(i).getFirstName());
            }
            counter ++;
        }
        ArrayList<Person> randomPeople = new ArrayList<>();
        randomPeople.add(person1);
        randomPeople.add(person2);

        return randomPeople;
    }

    /*Find the Degree of separation from the two random people in the graph*/
    public int degreeOfSeparationRandom(Person source, Person destination){
        /*Done Using the Breadth First Search Algo*/

        Set<Person> visited = new HashSet<>();
        Queue<Person> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();
        int currentLevel = 0;
        queue.add(source);
        level.add(currentLevel);

        System.out.println(source.getFirstName() +  " " + source.getLastName() + " is Separated from " + destination.getFirstName() + " " + destination.getLastName());

        while(!queue.isEmpty()){

            Person currentPerson = queue.poll();
            currentLevel = level.poll();

            if(visited.contains(currentPerson))
                continue;

            visited.add(currentPerson);

            if(currentPerson == destination)
                return currentLevel;

            if (source.getRelation().getEmployer().equals(destination.getRelation().getEmployer())
                        || source.getRelation().getResCom().equals(destination.getRelation().getResCom())
                        || source.getRelation().getSchool().equals(destination.getRelation().getSchool())) {

                return currentLevel+1;
            }

            for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
                if(entry.getKey() == currentPerson){
                    for (int i = 0; i <= map.size(); i++) {
                        queue.add(map.get(entry.getKey()).get(i));
                        level.add(currentLevel+1);
                    }
                }
            }

        }

        System.out.println(source.getFirstName() +  " " + source.getLastName() +
                " is Separated from " + destination.getFirstName() + " " + destination.getLastName() + " by " + currentLevel + " degrees");
        return currentLevel;
    }


    public void associateActivities(List<Activity> activities){
        //activities.sort(Comparator.comparing(Activity::getFirstName));
        for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
            Person p = entry.getKey();
            System.out.println(p.getFirstName() + " " + p.getLastName());
            for (int i = 0; i <= activities.size()-1; i++) {
                Activity a = activities.get(i);
                if(p.firstName.equals(a.getFirstName())
                        && p.lastName.equals(a.getLastName())){

                    p.appendActivity(a);

                    System.out.println(" is: " + p.printActivity());
                }
            }
        }
    }

    public void recommendationEngine(){

        int activitySize =0 ;
        for (Map.Entry<Person, List<Person>> root : map.entrySet()) {
            Person rootPerson = root.getKey();
            for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
                Person entryPerson = entry.getKey();
                if (entry.getKey() == root.getKey()
                        && ! root.getKey().isReqPrivacy()
                        && (rootPerson.getRelation().getEmployer().equals(entryPerson.getRelation().getEmployer())
                        || rootPerson.getRelation().getResCom().equals(entryPerson.getRelation().getResCom())
                        || rootPerson.getRelation().getSchool().equals(entryPerson.getRelation().getSchool()))){

                    if(rootPerson.getActivity().size() > entryPerson.getActivity().size())
                        activitySize =rootPerson.getActivity().size();
                    else
                        activitySize = entryPerson.getActivity().size();

                    for (int i = 0; i <= activitySize-1; i++) {
                        Activity activity= rootPerson.getActivity().get(i);
                        if(!entryPerson.getActivity().contains(activity)){
                            entryPerson.appendRecommendedActivity(activity);
                        }
                    }

                    System.out.println("Suggesting to " + root.getKey().getFirstName() + " " + root.getKey().getLastName() +
                                               " to add " + entry.getKey().printRecommendedActivity() + " to his/her activity list");
                }
            }
        }
    }

}