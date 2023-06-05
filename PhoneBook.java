package phonebook;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manskers
 */
public class PhoneBook {
    private ConsoleIO io;
    private String name;
    private List <Person> people;

    public PhoneBook(String name){
        this.name=name;
        people = new ArrayList<>();
    }
    
    public void addPersonToBook(Person person){
      people.add(person);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPeople() {
        return people;
    }
    
    @Override
    public String toString() {
        return name + "\n"+people;
    }

}

