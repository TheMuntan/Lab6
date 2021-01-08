package person;

import java.util.HashMap;

public class Person {
    String name;
    HashMap<Person, Double> owesList = new HashMap<>();

    public Person(String name) {
        this.name = name;
    }

    public void owes(Person person, Double amount) {

        if (owesList.containsKey(person)) {
            owesList.put(person, owesList.get(person) + amount);
        } else
            owesList.put(person, amount);
    }

    public HashMap<Person, Double> getOwesList() {
        return owesList;
    } 

    public String getName() {
        return name;
    }

}
