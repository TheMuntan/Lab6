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
      Person mikail = pFactory.getPerson("Mikail");
      serdar.owes(mikail, 34.24);
      System.out.println(serdar.getOwesList());
      System.out.println(serdar.getOwesList().containsKey(mikail));
      serdar.owes(mikail, 36.76);
      System.out.println(serdar.getOwesList());
      System.out.println(serdar.getOwesList().keySet());



      Tracker monTracker = Tracker.getInstance();


    }

}
