package service;

import model.Mapper.CustomerMapper;
import model.dao.CustomerDao;
import model.dao.CustomerDaoImpl;
import model.dto.AddCustomerDto;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 12:20 AM
 */
public class CustomerServiceImpl implements CustomerService{
    private final CustomerDao customerDao = new CustomerDaoImpl();


    @Override
    public List<CustomerDto> getAll() {
       return customerDao.queryAllCustomers().stream().map(CustomerMapper::fromCustomerToCustomerDto).toList();
    }

    @Override
    public List<CustomerDto> searchCustomerByName(String name) {
        return customerDao.searchCustomerByName(name).stream().map(CustomerMapper::fromCustomerToCustomerDto).toList();
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        return CustomerMapper.fromCustomerToCustomerDto(customerDao.queryCustomerById(id));
    }

    @Override
    public int addNewCustomer(AddCustomerDto addCustomerDto) {

        return customerDao.addNewCustomer(CustomerMapper.fromCustomerDtoToCustomer(addCustomerDto));
    }

    @Override
    public int updateCustomerById(Integer id) {
        return customerDao.updateCustomerById(id);
    }

    @Override
    public int deleteCustomerById(Integer id) {
        return customerDao.deleteCustomerById(id);
    }
}
