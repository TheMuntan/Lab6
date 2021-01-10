package calculator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import database.PersonDatabase;
import database.TicketDatabase;
import person.Person;
import ticket.Ticket;

public class Calculator {
    private static Calculator instance = null;
    private PersonDatabase pList;
    private TicketDatabase tList;
    private HashMap<HashMap<Person, Person>, Double> tripleHashMap = new HashMap<>();
    private HashMap<Person, Person> entryMap = new HashMap<>();

    private Calculator() {
    }

    public HashMap<HashMap<Person, Person>, Double> calculateFinalTotal(PersonDatabase pList) {  //returned triple hashmap has values: 1. amount owed 2. person that owes 3. person that is owed
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
                        tripleHashMap.put(entryMap, owedAmount);
                    }

                }
            }

        }
        return tripleHashMap;
    }


    public static Calculator getInstance() { // singleton pattern
        if (instance == null)
                instance = new Calculator();

        return instance;
    }

    public static void setInstance(Calculator instance) {
        Calculator.instance = instance;
    }

    public PersonDatabase getpList() {
        return pList;
    }

    public void setpList(PersonDatabase pList) {
        this.pList = pList;
    }


    public HashMap<HashMap<Person, Person>, Double> getTripleHashMap() {
        return tripleHashMap;
    }

    public void setTripleHashMap(HashMap<HashMap<Person, Person>, Double> tripleHashMap) {
        this.tripleHashMap = tripleHashMap;
    }

    public HashMap<Person, Person> getEntryMap() {
        return entryMap;
    }

    public void setEntryMap(HashMap<Person, Person> entryMap) {
        this.entryMap = entryMap;
    }

}