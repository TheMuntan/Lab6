package factory;

import person.Person;

public class PersonFactory extends AbstractFactory {


    public Person getPerson(String name) { return new Person(name); }

}
