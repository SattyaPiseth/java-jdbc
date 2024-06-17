package model.dao;

import model.entity.Order;

import java.util.List;

/**
 * @author Sattya
 * create at 6/16/2024 1:44 AM
 */
public interface OrderDao {
    Order getOrderById(Integer id);
    List<Order> getAllOrders();
    int addNewOrder(Order order);
    int updateOrderById(Integer id);
    int deleteOrder(Integer id);
}
