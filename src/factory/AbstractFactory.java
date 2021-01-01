package factory;

import person.Person;

public class AbstractFactory {


    public PersonFactory getPersonFact() {
        return new PersonFactory();
    }

}
