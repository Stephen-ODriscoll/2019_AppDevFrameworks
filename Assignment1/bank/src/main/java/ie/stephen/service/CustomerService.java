package ie.stephen.service;

public interface CustomerService {

    void newCustomer(String firstName, String lastName);
    boolean exists(int customerId);
    boolean displayInfo(int customerId);
}
