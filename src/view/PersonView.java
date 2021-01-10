package view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import person.Person;

public class PersonView {

    public void printPersonDetails (String personName, HashMap<Person, Double> owedList) {
        System.out.println("Person: "); 
        System.out.println("        Name: " + personName); 

        if (owedList != null) {
            System.out.println("        Owed money by: "); 

            Iterator it = owedList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Person, Double> pair = (Map.Entry)it.next();
                System.out.println("        " + pair.getKey().getName() + " owes €" + pair.getValue() + " to " + personName + ".");
            }
        } else {
            System.out.println("        Not owed money by anyone."); 
        }
    }
}
