package test;

import java.util.HashMap;

import factory.AbstractFactory;
import factory.FactoryProducer;
import org.testng.Assert;
import org.testng.annotations.Test;


import person.Person;

public class Person_UTest {

    @Test
    public void test() {

        AbstractFactory pFactory = FactoryProducer.getFactory("person");
        Person serdar = pFactory.getPerson("Serdar");
        Person luffy = pFactory.getPerson("Luffy");
        Person broly = pFactory.getPerson("Broly");
        serdar.owedBy(luffy, 34.24);
        serdar.owedBy(luffy, 36.76);
        serdar.owedBy(broly, 153.0);
        serdar.owedBy(broly, 452.0);
        HashMap<Person, Double> correctHMap = new HashMap<>();
        correctHMap.put(luffy, 71.00);
        correctHMap.put(broly, 605.00);

        Assert.assertEquals(correctHMap, serdar.getOwedList(), "Testing the person class - should return HashMap of owed people");


    }
    
}
