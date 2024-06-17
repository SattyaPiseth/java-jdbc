package service;

import model.dto.AddCustomerDto;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 12:16 AM
 */
public interface CustomerService {
    List<CustomerDto> getAll();
    List<CustomerDto> searchCustomerByName(String name);
    CustomerDto getCustomerById(Integer id);
    int addNewCustomer(AddCustomerDto addCustomerDto);
    int updateCustomerById(Integer id);
    int deleteCustomerById(Integer id);

}
