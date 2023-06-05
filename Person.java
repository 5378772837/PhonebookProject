
package phonebook;

/**
PEOPLE
first Name (String)
last Name (String)
possibly middle Name (String)
address (Address)
phone Number (long)
 *
 * @author Manskers
 */
public class Person {
    private String firstName;
    private String lastName;
    private String middleInitial="N/A";
    private String fullName;
    Address address;
    private long phoneNum;

    public Person(String lastName, String firstName, String middleInitial, int houseNumber, String street, String city, String state, int zip, long phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.phoneNum=phoneNumber;
        this.fullName = lastName+", "+firstName+" "+middleInitial;
        this.address = new Address(houseNumber,street,city,state,zip);
        
    }

    @Override
    public String toString() {
        return fullName + "  Phone Number: " + phoneNum + "\n"+this.address.toString()+"\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(){
        this.fullName = lastName+", "+firstName+", "+middleInitial;
    }
    public long getPhoneNumber() {
        return phoneNum;
    }
    public void setPhoneNumber(long phoneNum) {
        this.phoneNum = phoneNum;
    }
 
}
