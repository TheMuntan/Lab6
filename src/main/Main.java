package main;

import factory.AbstractFactory;
import factory.FactoryProducer;
import gui.Frame;
import person.Person;

public class Main {

    public static void main(String args[]) {
        Main main = new Main();
        main.run();
    }

    public Main() {

    }

    public void run() {
      Frame gui = new Frame();

      AbstractFactory pFactory = FactoryProducer.getFactory("person");
      Person lol = pFactory.getPerson("Lol");
      Person haha = pFactory.getPerson("Haha");
      lol.owedBy(haha, 34.24);
      System.out.println(lol.getOwedList());
      System.out.println(lol.getOwedList().containsKey(haha));
      lol.owedBy(haha, 36.76);
      System.out.println(lol.getOwedList());
      System.out.println(lol.getOwedList().keySet());


    }

}
