package database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import person.Person;
import ticket.Ticket;

public class PersonDatabase {
    private List<Person> pList = new ArrayList<>();


    public void add(Person p) {
        pList.add(p);
    }

    public Person get(int i) {
        return pList.get(i);
    }

    public int size() {
        return pList.size();
    }

    public Iterator iterator() {
        return pList.iterator();
    }


}
