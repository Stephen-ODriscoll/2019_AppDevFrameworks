package ie.stephen.model;

public class CustomerImplementation implements  Customer{

    private int customerId;
    private String firstName;
    private String lastName;

    public CustomerImplementation () { }

    public CustomerImplementation (int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String toString() {
        return firstName + " " + lastName;
    }
}
