package ticket;

import person.Person;

public class Ticket {

    private Person[] pList;
    private boolean evenSplit;
    private int total;

    public Ticket(int total, Person[] pList, boolean evenSplit) {
        this.total = total;
        this.pList = pList;
        this.evenSplit = evenSplit;
    }

    public Person[] getpList() {
        return pList;
    }

    public boolean isEvenSplit() {
        return evenSplit;
    }

    
}
