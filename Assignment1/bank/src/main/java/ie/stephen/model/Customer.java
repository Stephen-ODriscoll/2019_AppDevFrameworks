package ie.stephen.model;

public interface Customer {

    int getCustomerId();
    String getFirstName();
    String getLastName();

    void setCustomerId(int customerId);
    void setFirstName(String firstName);
    void setLastName(String lastName);

    String toString();
}
