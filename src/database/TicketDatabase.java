package database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ticket.Ticket;

public class TicketDatabase {

    private List<Ticket> tList = new ArrayList<>();


    public void add(Ticket t) {
        tList.add(t);
    }

    public Ticket get(int i) {
        return tList.get(i);
    }

    public int size() {
        return tList.size();
    }

    public Iterator iterator() {
        return tList.iterator();
    }

    public  boolean isEmpty() {
        return tList.isEmpty();

    }

}
