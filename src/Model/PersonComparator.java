package Model;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2){
            if(Long.parseLong(p1.getPhoneNumber()) > Long.parseLong(p2.getPhoneNumber())){
                return 1;
            }
            else if(Long.parseLong(p1.getPhoneNumber()) < Long.parseLong(p2.getPhoneNumber())){
                return -1;
            }
            else{
                return 0;
            }
    }
}

