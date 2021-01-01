package factory;

public class AbstractFactory {


    public PersonFactory getPersonFact() {
        return new PersonFactory();
    }

}
