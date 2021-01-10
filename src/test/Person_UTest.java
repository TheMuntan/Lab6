package test;

import java.util.HashMap;

import factory.AbstractFactory;
import factory.FactoryProducer;
import org.testng.Assert;
import org.testng.annotations.Test;


import person.Person;

public class Person_UTest {

    @Test
    public void getOwedListTest() {

        Person serdar = new Person("Serdar");
        Person luffy = new Person("Luffy");
        Person broly = new Person("Broly");

        serdar.owedBy(luffy, 34.24);
        serdar.owedBy(luffy, 36.76);
        serdar.owedBy(broly, 153.0);
        serdar.owedBy(broly, 452.0);

        HashMap<Person, Double> correctHMap = new HashMap<>(); //expected output
        correctHMap.put(luffy, 71.00);
        correctHMap.put(broly, 605.00);

        Assert.assertEquals(correctHMap, serdar.getOwedList());

    }

    @Test
    public void setOwedListTest() {

        Person serdar = new Person("Serdar");
        Person luffy = new Person("Luffy");
        Person broly = new Person("Broly");

        HashMap<Person, Double> correctHMap = new HashMap<>(); //expected output
        correctHMap.put(luffy, 71.00);
        correctHMap.put(broly, 605.00);

        serdar.setOwedList(correctHMap);


        Assert.assertEquals(correctHMap, serdar.getOwedList());

    }

    @Test
    public void getNameTest() {
        Person serdar = new Person("Serdar");

        Assert.assertEquals("Serdar", serdar.getName());
    }

}
