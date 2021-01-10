package test;

import controller.PersonController;
import factory.AbstractFactory;
import factory.FactoryProducer;
import org.testng.Assert;
import org.testng.annotations.Test;
import person.Person;
import view.PersonView;

public class integration_test {


    //testing the integration between the FactoryProducer, AbstractFactory and the MVC classes
    @Test
    public void factoryMVC_integration_test() {

        AbstractFactory pFactory = FactoryProducer.getFactory("person");
        Person person = pFactory.getPerson("Person");
        PersonView pView = new PersonView();

        PersonController personController = new PersonController(person, pView);

        Assert.assertEquals("Person", personController.getPersonName());
        personController.setPersonName("newName");
        Assert.assertEquals("newName", personController.getPersonName());


    }

}
