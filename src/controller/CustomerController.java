package controller;
import model.dto.AddCustomerDto;
import model.dto.CustomerDto;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.util.List;


/**
 * @author Sattya
 * create at 6/17/2024 1:49 AM
 */
public class CustomerController {
    public final CustomerService customerService = new CustomerServiceImpl();

    public List<CustomerDto> getAllCustomers() {
        return customerService.getAll();
    }
    public CustomerDto getCustomerById(Integer id) {
        return customerService.getCustomerById(id);
    }
    public int addNewCustomer(AddCustomerDto addCustomerDto) {
        return customerService.addNewCustomer(addCustomerDto);
    }
    public List<CustomerDto> searchCustomerByName(String name) {
        return customerService.searchCustomerByName(name);
    }
    public int updateCustomerById(Integer id) {
        return customerService.updateCustomerById(id);
    }
    public int deleteCustomerById(Integer id) {
        return customerService.deleteCustomerById(id);
    }
}

