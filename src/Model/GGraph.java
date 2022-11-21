/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*
* * // Date Created: 2021-03-29
* Title: Aoa Group Project
*/

package Model;


import java.util.*;
import java.util.stream.Collectors;

public class GGraph {

    /*Everyone in the file mapped with friends*/
    Map<Person, List<Person>> map = new HashMap<>();

    /*Everyone in the file*/
    List<Person> personList = new ArrayList<>();
    List <Activity>activityList = new ArrayList<>();
    TreeMap<Person, List<Person>> treeMap = new TreeMap<>(new PersonComparator());

    ArrayList<Person> personActList = new ArrayList<>(); /*This is everyone who participates in an activity*/

    /*Testing value for the file size*/
    final int SIZE = 100;

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
                if (person.isRelatedTo(person1)) {
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

            if(currentPerson == destination){
                return currentLevel;

            }else {
                for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {

                    if (entry.getKey().equals(destination)) {
                        return ++currentLevel;
                    }
                        queue.add(entry.getKey());
                        level.add(currentLevel++);
                }
            }
        }

        System.out.println(source.getFirstName() +  " " + source.getLastName() +
                " is Separated from " + destination.getFirstName() + " " + destination.getLastName() + " by " + currentLevel + " degrees");
        return currentLevel;
    }

    public int degreeOfSeparation(Person source, Person destination){
        /*Done Using the Breadth First Search Algo*/

        Set<Person> visited = new HashSet<>();
        Queue<Person> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();
        int currentLevel = 0;
        queue.add(source);
        level.add(currentLevel);

        while(!queue.isEmpty()){

            Person currentPerson = queue.poll();
            currentLevel = level.poll();

            if(visited.contains(currentPerson))
                continue;

            visited.add(currentPerson);

            if(currentPerson == destination){
                return currentLevel;

            }else {
                for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {

                    if (entry.getKey().equals(destination)) {
                        return ++currentLevel;
                    }
                        queue.add(entry.getKey());
                        level.add(currentLevel++);
                }
            }
        }
        return currentLevel;
    }

    //find the average degree of separation for all nodes in the graph
    public int averageDegreeOfSeparation(){
        int total = 0;
        int counter = 0;
        for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
            for (Map.Entry<Person, List<Person>> entry1 : map.entrySet()) {
                if(entry.getKey() != entry1.getKey()){
                    total += degreeOfSeparation(entry.getKey(),entry1.getKey());
                    counter++;
                }
            }
        }

        System.out.println("Average Degree of Separation: " + total/counter);
        return total/counter;
    }


    public void associateActivities(List<Activity> activities) {

         activityList = activities;
        //activities.sort(Comparator.comparing(Activity::getFirstName));

        /*create a binary search tree from tempPersonActList*/
        treeSet = new TreeSet<>(new PersonComparator());

        /*Persons with activities*/
        personActList = new ArrayList<>();
        ArrayList<Person> tempPersonActList = new ArrayList<>();


        /*Finding all persons who have activities associated with them*/

        /*using a red black tree*/
        TreeMap<Person, List<Person>> tempMap = new TreeMap<>(new PersonComparator());
        tempMap.putAll(map);

        //create a tree map set

        for (Activity a : activities) { // O(n) * O(log n) = O(n log n)
            //check if the firstname and last name is present in the tree map without a loop
            //if present add the activity to the person
            //if not present create a new person and add the activity to the person
            //add the person to the tree map  Ologn
            //The map is sorted according to the natural ordering of its keys

            Person person = new Person();
            person.setFirstName(a.getFirstName());
            person.setLastName(a.getLastName());

            /*is the first name and last name presnt in the list?
             * if yes we append the associated activity with that person*/

            tempMap.entrySet().stream().filter(entry->entry.getKey().getFirstName().equals(person.getFirstName())
                            && entry.getKey().getLastName().equals(person.getLastName()))
                    .forEach(entry->{
                        entry.getKey().appendActivity(a.getActivityName(),a.getFirstName(),a.getLastName());
                        //remove duplicates activities
                        entry.getKey().removeDuplicates();
                        personActList.add(entry.getKey());
                    });
            //remove duplicates from activity list
            personActList = (ArrayList<Person>) personActList.stream().distinct().collect(Collectors.toList());
        }


        treeMap = tempMap;

        //map = tempMap;
        //print the values in treemap
        tempMap.entrySet().stream().forEach(entry->System.out.println(entry.getKey().getFirstName()
                                                                              + " " + entry.getKey().getLastName()
                                                                              + " " + entry.getKey().printActivity()));
    }



        /*for (Activity a : activities) { // O(n)
            //Sort list by first name
            for (Person p : personList) { // O(n)
                if (a.getFirstName().equals(p.getFirstName()) && a.getLastName().equals(p.getLastName())) {
                    tempPersonActList.add(p);
                }
            }*/

            /*Not working*/
            /*Person p = binarySearchName(a.firstName, a.lastName); // O(log n) nlogn = O(nlogn)
            if (p != null) {
                p.appendActivity(a.getActivityName());
                tempPersonActList.add(p);
            }*/
                /*if(person.getFirstName().equals(activity.getFirstName()) && person.getLastName().equals(activity.getLastName())){
                    personActList.add(person);
                }*/

        /*int i =0;
        for(Person p : tempPersonActList){ // O(n)
            Activity a = activities.get(i);
            //if(treeSet.contains(p) &&  ( p.getFirstName().equals(a.getFirstName()) && p.getLastName().equals(a.getLastName()) )){
                Person rightNode  = treeSet.ceiling(p); // O(log n)
                Person leftNode = treeSet.floor(p);
                if(rightNode == leftNode){
                    p.appendActivity(a.activityName);
                    p.removeDuplicates();
                    System.out.println(p.getFirstName() + " " + p.getLastName() + "\n\t" + p.printActivity());
                    personActList.add(p);
                }
           // }

            i++;
            if(i == activities.size())
                break;
        }

        treeSet.addAll(personActList);*/

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


    public void recommendationEngine() {

        TreeMap<Person, List<Person>> tempMap = new TreeMap<>(new PersonComparator());
        tempMap.putAll(treeMap);


        //recommend activity to people who are related using the tempMap filter stream

        //for each person in the map find the person who is related to them
        //if the person is related to them then recommend the activity to them
        //if the person is not related to them then do not recommend the activity to them
        //if the person doesnt not request privacy then recommend the activity to them


        for (Map.Entry<Person, List<Person>> entry : tempMap.entrySet()) {
            List<Person> people = entry.getValue();
            Iterator<Person> it = people.iterator();
            for (Person person : personActList) {
                //check if the current key has the same person list value
                if (tempMap.containsValue(people)) {

                    Person friend = it.next();

                    //check if p and person have the same activity
                    //if they do not have the same activity then recommend the activity to person
                    if (! person.containSameActivity(friend)) {
                        List<Activity> al = friend.getActivity();
                        al.addAll(person.getActivity());
                        Set unique = new HashSet(al);
                        al = (List<Activity>) unique.stream().collect(Collectors.toList());

                        //CollectionUtils.dis
                        //find the activity that p has that person does not have
                        //recommend the activity to person

                        for (Activity a : al) {
                            //if(!friend.containSameActivity(a))
                            friend.appendRecommendedActivity(a);

                        }
                        System.out.println("Recommend " + friend.printRecommendedActivity() + " to " + friend.getFirstName() + " " + friend.getLastName());
                        System.out.println();
                    }
                }

        /*for(Map.Entry<Person, List<Person>> entry : tempMap.entrySet()){
            if(tempMap.containsKey(entry.getKey())){
                //get the list of persons who are related to the person
                List<Person> relatedPersons = tempMap.get(entry.getKey());
                //get the list of activities of the person
                List<Activity> activities = entry.getKey().getActivity();
                //for each activity of the person recommend the activity to the related persons
                for(Activity activity : activities){
                    for(Person person : relatedPersons){
                        //if the person is not related to the person then recommend the activity to them
                        if(!person.isRelatedTo(entry.getKey())){
                            //if the person does not request privacy then recommend the activity to them
                            if(!person.isReqPrivacy()){
                                person.appendRecommendedActivity(activity.getActivityName());
                                System.out.println( "We are recommending " + activity.getActivityName() + " to " + person.getFirstName() + " " + person.getLastName());
                            }
                        }
                    }
                }
            }
        }*/




        /*for(Activity a: activityList) {
            Person person = new Person();
            person.setFirstName(a.getFirstName());
            person.setLastName(a.getLastName());
            tempMap.entrySet().stream().filter(entry->entry.getKey().getFirstName().equals(person.getFirstName())
                            && entry.getKey().getLastName().equals(person.getLastName()))
                    .forEach();*/



        /*tempMap.entrySet().stream().filter( entry -> entry.getKey().getActivity().size() > 1)
                .forEach(entry -> {
                    System.out.println(entry.getKey().getFirstName() + " " + entry.getKey().getLastName() + " " + entry.getKey().printActivity());
                    entry.getValue().stream().forEach(person -> System.out.println("\t" + person.getFirstName() + " " + person.getLastName() + " " + person.printRecommendedActivity()));
                });*/
            }

       /* Set<Person> visited = new HashSet<>();

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
                        if(!entryPerson.getActivity().contains(activity)){
                            entryPerson.appendRecommendedActivity(activity);
                        }
                    }
                    entryPerson.removeDuplicates();
                    System.out.println("Suggesting to " + root.getFirstName() + " " + root.getLastName() +
                                               " to add " + entry.printRecommendedActivity() + " to his/her activity list");
                }
            }
        }*/
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


    public Person binarySearchName(String firstName, String lastName){

        ArrayList<Person> sortedPersonList = new ArrayList<>(personList);
        sortedPersonList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        });
        int left = 0;
        int right = personList.size() - 1;
        int mid = 0;
        while(left <= right){
            mid = (left + right) / 2;
            String midFirstName = sortedPersonList.get(mid).getFirstName();
            //String midLastName = personList.get(mid).getLastName();
            if(midFirstName.equals(firstName) /*&& midLastName.equals(lastName)){
                return personList.get(mid);
            }else if((midFirstName.compareToIgnoreCase(firstName) < 0) /*&& (midLastName.compareToIgnoreCase(lastName) < 0)*/){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return null;
    }


}