package main;

import factory.AbstractFactory;
import factory.PersonFactory;
import gui.Frame;
import person.Person;
import tracker.Tracker;

public class Main {

    public static void main(String args[]) {
        Main main = new Main();
        main.run();
    }

    public Main() {

    }

    public void run() {
      Frame gui = new Frame();

        AbstractFactory aFactory = new AbstractFactory();
        PersonFactory pFactory = aFactory.getPersonFact(); //???
        Person serdar = pFactory.getPerson("Serdar");
        System.out.println(serdar.getName());

      Tracker monTracker = Tracker.getInstance();


    }

}
