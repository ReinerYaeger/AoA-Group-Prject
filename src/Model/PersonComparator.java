/*
*   Chevaughn Gibson 1900396,
*   Gail-Ann Archer 2002407,
*   Lashea Beaton 2003885,
*   Jermaine Graham 1704263
*/

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

