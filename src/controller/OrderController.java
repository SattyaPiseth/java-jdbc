package controller;

import exception.OrderNotFoundException;
import model.dto.AddOrderDto;
import model.dto.OrderDto;
import service.OrderService;
import service.OrderServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 2:44 AM
 */
public class OrderController {
    private final OrderService orderService = new OrderServiceImpl();
    public List<OrderDto> getAllOrders(){
        return orderService.getAll();
    }
    public OrderDto getOrderById(Integer id){
        return orderService.getOrderById(id);
    }
    public int addNewOrder(AddOrderDto addOrderDto){
        return orderService.addNewOrder(addOrderDto);
    }
    public int updateOrder(Integer id){
        return orderService.updateOrder(id);
    }
    public int deleteOrderById(Integer id){
        return orderService.deleteOrder(id);
    }
}
