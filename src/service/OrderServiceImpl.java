package service;

import exception.OrderNotFoundException;
import model.Mapper.OrderMapper;
import model.dao.OrderDaoImpl;
import model.dto.AddOrderDto;
import model.dto.OrderDto;
import model.entity.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 12:44 AM
 */
public class OrderServiceImpl implements OrderService{
    private final OrderDaoImpl orderDao = new OrderDaoImpl();


    @Override
    public List<OrderDto> getAll() {
        return orderDao.getAllOrders().stream().map(OrderMapper::fromOrderToOrderDto).toList();
    }

    @Override
    public OrderDto getOrderById(Integer id)  {
        return OrderMapper.fromOrderToOrderDto(orderDao.getOrderById(id));
    }

    @Override
    public int addNewOrder(AddOrderDto addOrderDto) {
       return orderDao.addNewOrder(OrderMapper.fromAddOrderDtoToOrder(addOrderDto));
    }

    @Override
    public int updateOrder(Integer id) {
        return orderDao.updateOrderById(id);
    }

    @Override
    public int deleteOrder(Integer id) {
        return orderDao.deleteOrder(id);
    }
}
