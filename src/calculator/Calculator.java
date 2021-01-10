package calculator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import person.Person;
import ticket.Ticket;

public class Calculator {
    private static Calculator instance = null;
    private List<Person> pList;
    private List<Ticket> tList;
    private HashMap<Double, HashMap<Person, Person>> tripleHashMap = new HashMap<>();
    private HashMap<Person, Person> entryMap = new HashMap<>();

    private Calculator() {
    }

    public HashMap<Double, HashMap<Person, Person>> calculateFinalTotal(List<Person> pList) {  //returned triple hashmap has values: 1. amount owed 2. person that owes 3. person that is owed
        tripleHashMap = new HashMap<>();

        for (int i = 0; i < pList.size(); i++) {
            HashMap<Person, Double> finalOwedList = pList.get(i).getOwedList();
            if (finalOwedList != null) {
                Iterator it = finalOwedList.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Person, Double> pair = (Map.Entry)it.next();
                    Person owedPerson = pair.getKey();
                    Double owedAmount = pair.getValue();
                    HashMap<Person, Double> tempOwedList = owedPerson.getOwedList();
                    if (tempOwedList != null) {
                        Iterator it2 = tempOwedList.entrySet().iterator();
                        while (it2.hasNext()) {
                            Map.Entry<Person, Double> pair2 = (Map.Entry)it2.next();
                            if (pair2.getKey() == pList.get(i)) {
                                owedAmount -= pair2.getValue();
                            }
                        }
                    }

                    if (owedAmount >= 0.0) {
                        entryMap = new HashMap<>();
                        entryMap.put(owedPerson, pList.get(i));
                        tripleHashMap.put(owedAmount, entryMap);
                    }

                }
            }

        }
        return tripleHashMap;
    }

    public List<Person> getpList() {
        return pList;
    }

    public void setpList(List<Person> pList) {
        this.pList = pList;
    }

    public List<Ticket> getTicketList() {
        return tList;
    }

    public void setTicketList(List<Ticket> tList) {
        this.tList = tList;
    }

    public static Calculator getInstance() { // singleton pattern
        if (instance == null)
                instance = new Calculator();

        return instance;
    }

}