package model.dao;

import model.entity.Customer;

import java.util.List;

/**
 * @author Sattya
 * create at 6/15/2024 12:57 PM
 */
public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int updateCustomerById(Integer id);
    int deleteCustomerById(Integer id);
    int addNewCustomer(Customer customer);
    List<Customer> searchCustomerByName(String name);
    Customer queryCustomerById(Integer id);
}
