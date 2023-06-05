package phonebook;

/**
 * ADDRESS:
House/Apt. number (int)
street name (String)
city (String)
state (String)
zip code (int)
 *
 * @author Manskers
 */
public class Address {
    private int houseNumber = 0;
    private String street = "";
    private String city = "";
    private String state = "";
    private int zip = 0;

    @Override
    public String toString() {
        return houseNumber + " " + street + ", " + city + ", " + state + " " + zip;
    }

    public Address(int houseNumber, String street, String city, String state,int zip) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    
    
    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
    
}
