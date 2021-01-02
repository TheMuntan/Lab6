package ticket;

import person.Person;

public class Ticket {

    private Person[] pList;
    private boolean evenSplit;

    public Ticket(Person[] pList, boolean evenSplit) {
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
