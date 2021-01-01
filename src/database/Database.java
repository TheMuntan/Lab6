package database;

import person.Person;
import ticket.Ticket;

public abstract class Database {
    public Database()
    {

    }

    public abstract void addEntry(Person e, Ticket t);

}
