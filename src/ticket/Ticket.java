package ticket;

import person.Person;
import java.util.HashMap;

public class Ticket {

    private int total;
    private HashMap<Person, Double> pHash;
    private Person payed;

    public Ticket(int total, HashMap<Person, Double> pHash, Person payed) {
        this.total = total;
        this.pHash = pHash;
        this.payed = payed;
    }

    
}
