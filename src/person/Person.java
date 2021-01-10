package person;

import java.util.HashMap;

public class Person {
    String name;
    HashMap<Person, Double> owedList = new HashMap<>();
    boolean hasOwedList = false;

    public Person(String name) {
        this.name = name;
    }

    public void owedBy(Person person, Double amount) {

        if (owedList.containsKey(person)) {
            owedList.put(person, owedList.get(person) + amount);
        } else {
            owedList.put(person, amount);
        }
        hasOwedList = true;
    }

    public HashMap<Person, Double> getOwedList() {
        if (hasOwedList)
            return owedList;
        else
            return null;
    }

    public String getName() {
        return name;
    }

    public void setOwedList(HashMap<Person, Double> owedList) {
        hasOwedList = true;
        this.owedList = owedList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasOwedList() {
        return hasOwedList;
    }

    public void setHasOwedList(boolean hasOwedList) {
        this.hasOwedList = hasOwedList;
    }

}
