package controller;

import person.Person;
import view.PersonView;

public class PersonController {
    private Person person;
    private PersonView viewer;

    public PersonController(Person person, PersonView viewer){
        this.person = person;
        this.viewer = viewer;
    }

    public void setPersonName(String name) 
    { 
        person.setName(name);         
    } 
  
    public String getPersonName() 
    { 
        return person.getName();         
    } 
  
    public void view() 
    {                 
        viewer.printPersonDetails(person.getName(), person.getOwedList()); 
    }    
    
}
