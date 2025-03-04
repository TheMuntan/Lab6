package factory;

import java.util.HashMap;

import person.Person;
import ticket.Ticket;

public class PersonFactory extends AbstractFactory {

    @Override
    public Person getPerson(String name) {
        return new Person(name);
    }

    @Override
    public Ticket getTicket(HashMap<Person, Double> pHash, Person payed) {
        return null;
    }

}
