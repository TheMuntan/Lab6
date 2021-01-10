package factory;

import java.util.HashMap;

import person.Person;
import ticket.Ticket;

public class TicketFactory extends AbstractFactory {

    @Override
    public Person getPerson(String name) {
        return null;
    }

    @Override
    public Ticket getTicket(HashMap<Person, Double> pHash, Person payed) {
        return new Ticket(pHash, payed);
    }

    
}
