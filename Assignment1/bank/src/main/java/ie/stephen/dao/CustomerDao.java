package ie.stephen.dao;

import ie.stephen.model.Customer;

public interface CustomerDao {

    int newCustomer(String firstName, String lastName) throws NullPointerException;
    Customer findById(int id);
}
