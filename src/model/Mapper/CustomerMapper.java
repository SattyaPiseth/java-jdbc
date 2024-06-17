package model.Mapper;

import model.dto.AddCustomerDto;
import model.dto.CustomerDto;
import model.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Sattya
 * create at 6/17/2024 1:58 AM
 */
public class CustomerMapper {
    public static CustomerDto fromCustomerToCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerDto(customer.getName(), customer.getEmail());
    }
    public static Customer fromCustomerDtoToCustomer(AddCustomerDto addCustomerDto) {
        if (addCustomerDto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(addCustomerDto.name());
        customer.setEmail(addCustomerDto.email());
        customer.setPassword(addCustomerDto.password());
        customer.setIsDeleted(false);
        customer.setCreatedDate(Date.valueOf(LocalDate.now()));
        // Continue for all properties of the Customer

        return customer;
    }
}
