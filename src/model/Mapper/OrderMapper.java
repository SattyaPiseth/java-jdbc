package model.Mapper;

import model.dto.AddOrderDto;
import model.dto.OrderDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sattya
 * create at 6/17/2024 2:48 AM
 */
public class OrderMapper {
    public static OrderDto fromOrderToOrderDto(Order order){
        return OrderDto.builder()
               .order_name(order.getOrderName())
               .order_description(order.getOrderDescription())
               .customer_name(order.getCustomer().getName())
                .customer_email(order.getCustomer().getEmail())
                .ordered_at(order.getOrderedAt().toString())
               .build();
    }

    public static Order fromAddOrderDtoToOrder(AddOrderDto addOrderDto) {
        if (addOrderDto == null) {
            return null;
        }

        Order order = new Order();
        System.out.print("[+] Enter order id: ");
        order.setId(new Scanner(System.in).nextInt());
        order.setOrderName(addOrderDto.order_name());
        order.setOrderDescription(addOrderDto.order_description());
        order.setCustomer(Customer.builder().id(addOrderDto.cus_id()).build());
        order.setOrderedAt(Date.valueOf(addOrderDto.order_at()));
        order.setProductList(addOrderDto.pro_id().stream().map(id -> Product.builder().id(id).build()).toList());
        return order;
    }
}
