package ticket;

import person.Person;
import java.util.HashMap;

public class Ticket {

    private HashMap<Person, Double> pHash;
    private Person payed;

    public Ticket(HashMap<Person, Double> pHash, Person payed) {
        this.pHash = pHash;
        this.payed = payed;
    }

    public HashMap<Person, Double> getpHash() {
        return pHash;
    }

    public Person getPayed() {
        return payed;
    }


    
}
