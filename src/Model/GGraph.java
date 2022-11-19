/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*/

package Model;


import java.util.*;

public class GGraph {

    /*Everyone in the file mapped with friends*/
    static Map<Person, List<Person>> map = new HashMap<>();

    /*Everyone in the file*/
    static List<Person> personList = new ArrayList<>();

    /*Testing value for the file size*/
    final int SIZE = 1000;

    /*People in tree, (with activities associated)*/
    TreeSet<Person> treeSet = new TreeSet<>(new PersonComparator());

    /*Just a Person Object*/
    Person person = new Person();

    public GGraph(List<Person> pl) {
        personList = pl;

        //pl.sort(Comparator.comparing(Person::getFirstName));
    }

    public void findFriends( ){
        /*People in Persons List Friends*/
        List<Person> personIList = new ArrayList<>();

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

    public void averageDegreeOfSeparation(){
        Set<Person> visited = new HashSet<>();
        Queue<Person> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();

        int degreeOfSeparation = 0;
        int counter = 0;
        int currentLevel = 0;
        Map.Entry<Person, List<Person>> p = map.entrySet().iterator().next();
        queue.add(p.getKey());
        level.add(currentLevel);


        for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {

            while(!queue.isEmpty()) {
                Person currentPerson = queue.poll();
                currentLevel = level.poll();

                if (visited.contains(currentPerson))
                    continue;
                visited.add(currentPerson);
                for (Map.Entry<Person, List<Person>> entryI : map.entrySet()) {
                    if (entry.getKey() == currentPerson) {
                        for (int i = 0; i <= map.size(); i++) {
                            queue.add(map.get(entryI.getKey()).get(i));
                            level.add(currentLevel + 1);
                            degreeOfSeparation += currentLevel + 1;
                            counter++;
                        }
                    }
                }
            }
        }
        degreeOfSeparation = degreeOfSeparation + currentLevel;

        System.out.println("Average Degree of Separation: " + degreeOfSeparation/counter);

    }


    public void associateActivities(List<Activity> activities) {
        //activities.sort(Comparator.comparing(Activity::getFirstName));

        /*create a binary search tree from tempPersonActList*/
        treeSet = new TreeSet<>(new PersonComparator());
        //temptreeset
        TreeSet<Person> tempTreeSet = treeSet;

        /*Persons with activities*/
        ArrayList<Person> personActList = new ArrayList<>();
        ArrayList <Person> tempPersonActList = new ArrayList<>();

        //sort PersonList store to a temp list
        List<Person> sortedPersonList = personList;
        sortedPersonList.sort(Comparator.comparing(Person::getFirstName));

        /*Finding all persons who have activities associated with them*/

        for (Activity a : activities) { // O(n)

            Person p = binarySearchName(a.firstName,sortedPersonList,a.lastName);
            if(sortedPersonList.contains(p)){
                p.appendActivity(a);
                tempPersonActList.add(p);
            }
           /* for (Person p : personList) { // O(n)
                if (a.getFirstName().equals(p.getFirstName()) && a.getLastName().equals(p.getLastName())) {
                    tempPersonActList.add(p);
                }
            }*/
        }
        treeSet.addAll(tempPersonActList);

            /*Not working*/
            /*Person p = binarySearchName(a.firstName, a.lastName); // O(log n) nlogn = O(nlogn)
            if (p != null) {
                p.appendActivity(a.getActivityName());
                tempPersonActList.add(p);
            }*/
                /*if(person.getFirstName().equals(activity.getFirstName()) && person.getLastName().equals(activity.getLastName())){
                    personActList.add(person);
                }*/

        int i =0;
        for(Person p : tempPersonActList){ // O(n)
            Activity a = activities.get(i);
            if(treeSet.contains(p) /*&&  ( p.getFirstName().equals(a.getFirstName()) && p.getLastName().equals(a.getLastName()) )*/){
                Person rightNode  = treeSet.ceiling(p); // O(log n)
                Person leftNode = treeSet.floor(p);
                if(rightNode == leftNode){
                    p.appendActivity(a.activityName);
                    p.removeDuplicates();
                    System.out.println(p.getFirstName() + " " + p.getLastName() + "\n\t" + p.printActivity());
                    personActList.add(p);
                }
           }
            i++;
            if(i == activities.size())
                break;
        }

        treeSet.addAll(personActList);

        /*for (Person p : treeSet) {
            Activity a = activities.stream().iterator().next();
            if (p.getFirstName().equals(a.getFirstName())
                    && p.getLastName().equals(a.getLastName())) {

                p.appendActivity(a.activityName);
                System.out.println(p.getFirstName() + " " + p.getLastName() + "\n\t" + a.printActivity());
            }
            // print the person name and activity
            System.out.println(p.getFirstName() + " " + p.getLastName() + " " + p.printActivity());
        }*/
        /*for(Activity activity : activities){
            Person person = new Person();
            person = tempPersonActList.stream().iterator().next();
            if(treeSet.contains(person)){
                Person tempPerson = (Person) treeSet.floor(person);
                tempPerson.appendActivity(activity);
                ((Person) treeSet.floor(person)).appendActivity(activity);
            }

            System.out.println("Tree: " + treeSet);
        }*/


        /*for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
            Person p = entry.getKey();
            System.out.println(p.getFirstName() + " " + p.getLastName());

            for (int i = 0; i <= tempPersonActList.size() - 1; i++) {

                if (tempPersonActList.get(i).getPhoneNumber().equals(p.getPhoneNumber())) {
                    Activity a = activities.get(i);
                    if (p.firstName.equals(a.getFirstName())
                            && p.lastName.equals(a.getLastName())) {

                        if (! ( p.getActivity().contains(a) ))
                            tempPersonActList.get(i).appendActivity(a);

                        System.out.println(" is: " + p.printActivity());
                    }
                }
            }
        }*/
    }

    public void recommendationEngine(){

        Set<Person> visited = new HashSet<>();

        int activitySize =0 ;
        for (Person root : treeSet){
            Person rootPerson = root;

            for (Person entry : treeSet) {
                Person entryPerson = entry;

                if(visited.contains(entryPerson))
                    continue;

                visited.add(entryPerson);

                if (entry == root
                        && ! root.isReqPrivacy()
                        && (rootPerson.getRelation().getEmployer().equals(entryPerson.getRelation().getEmployer())
                        || rootPerson.getRelation().getResCom().equals(entryPerson.getRelation().getResCom())
                        || rootPerson.getRelation().getSchool().equals(entryPerson.getRelation().getSchool()))){

                    if(rootPerson.getActivity().size() > entryPerson.getActivity().size())
                        activitySize =rootPerson.getActivity().size();
                    else
                        activitySize = entryPerson.getActivity().size();

                    for (int i = 0; i <= activitySize-1; i++) {
                        String activity= rootPerson.getActivity().get(i).getActivityName();
                        if(! ( entryPerson.getActivity() == rootPerson.getActivity() )){
                            entryPerson.appendRecommendedActivity(activity);
                        }
                    }
                    entryPerson.removeDuplicates();
                    System.out.println("Suggesting to " + root.getFirstName() + " " + root.getLastName() +
                                               " to add " + entry.printRecommendedActivity() + " to his/her activity list");
                }
            }
        }
    }
    //binary search by phone number
    public Person binarySearch(String phoneNumber){

        int left = 0;
        int right = SIZE - 1;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            if(personList.get(mid).getPhoneNumber().equals(phoneNumber)){
                return personList.get(mid);
            }else if(personList.get(mid).getPhoneNumber().compareTo(phoneNumber) < 0){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return null;
    }

    public boolean samePerson(Person p1, Person p2){
        if(p1.getFirstName().equals(p2.getFirstName()) && p1.getLastName().equals(p2.getLastName()))
            return true;
        else
            return false;
    }


    public Person binarySearchName(String firstName,List<Person> tempPersonList, String lastName){

        /*ArrayList<Person> sortedPersonList = new ArrayList<>(personList);
        sortedPersonList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });*/
        int left = 0;
        int right = personList.size() - 1;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            String midFirstName = tempPersonList.get(mid).getFirstName();
            String midLastName = tempPersonList.get(mid).getLastName();
            if(midFirstName.equals(firstName)&& midLastName.equals(lastName)){
                return personList.get(mid);
            }else if((midFirstName.compareToIgnoreCase(firstName) < 0) && (midLastName.compareToIgnoreCase(lastName) < 0)){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return null;
    }


}