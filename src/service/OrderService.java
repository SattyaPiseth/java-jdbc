package service;

import exception.OrderNotFoundException;
import model.dto.AddOrderDto;
import model.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 12:19 AM
 */
public interface OrderService {
    List<OrderDto> getAll();
    OrderDto getOrderById(Integer id);
    int addNewOrder(AddOrderDto addOrderDto);
    int updateOrder(Integer id);
    int deleteOrder(Integer id);
}
