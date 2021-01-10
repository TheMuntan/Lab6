package factory;

import java.util.HashMap;

import person.Person;
import ticket.Ticket;

public abstract class AbstractFactory {

    public abstract Person getPerson(String name);
    public abstract Ticket getTicket(HashMap<Person, Double> pHash, Person payed);
    

}
