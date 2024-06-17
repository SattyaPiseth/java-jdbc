package model.dao;

import connection.ConnectionPool;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sattya
 * create at 6/16/2024 2:53 PM
 */
public class OrderDaoImpl implements OrderDao{
    @Override
    public Order getOrderById(Integer id) {
        String sql = """
            SELECT o.*, c.name as customer_name, c.email as customer_email 
            FROM "order" o
            JOIN "customer" c ON o.cus_id = c.id
            WHERE o.id = ?
            """;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            while (resultSet.next()){
                order = Order.builder()
                        .id(resultSet.getInt("id"))
                        .orderName(resultSet.getString("order_name"))
                        .orderDescription(resultSet.getString("order_description"))
                        .customer(
                                Customer.builder()
                                        .id(resultSet.getInt("cus_id"))
                                        .name(resultSet.getString("customer_name"))
                                        .email(resultSet.getString("customer_email"))
                                        .build()
                        )
                        .orderedAt(resultSet.getDate("ordered_at"))
                        .build();
            }
            return order;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        String sql = """
            SELECT * FROM "order" INNER JOIN customer c ON "order".cus_id = c.id
            """;
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                orderList.add(
                        Order.builder()
                                .id(resultSet.getInt("id"))
                                .orderName(resultSet.getString("order_name"))
                                .orderDescription(resultSet.getString("order_description"))
                                .orderedAt(resultSet.getDate("ordered_at"))
                                .customer(Customer.builder()
                                        .id(resultSet.getInt("cus_id"))
                                        .name(resultSet.getString("name"))
                                        .email(resultSet.getString("email"))
                                        .password(resultSet.getString("password"))
                                        .isDeleted(resultSet.getBoolean("is_deleted"))
                                        .createdDate(resultSet.getDate("created_date"))
                                        .build())
                                .build()
                );
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return orderList;
    }

    @Override
    public int addNewOrder(Order order) {
        String sql = "INSERT INTO \"order\" (id,order_name, order_description, cus_id, ordered_at) VALUES (?, ?, ?, ?, ?)";
        String sql1 = """
            INSERT INTO product_order (pro_id, order_id) VALUES (?, ?);
            """;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)
        ) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setString(2, order.getOrderName());
            preparedStatement.setString(3, order.getOrderDescription());
            preparedStatement.setInt(4, order.getCustomer().getId());
            preparedStatement.setDate(5, order.getOrderedAt());
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected>0 ? "[+] Add new order successfully" : "[+] Add new order failed";
            System.out.println(message);

            // Product Order
            int rowAffected1 = 0;
            for (Product product : order.getProductList()){
                preparedStatement1.setInt(1, product.getId());
                preparedStatement1.setInt(2, order.getId());
                rowAffected1 += preparedStatement1.executeUpdate();
            }
            if (rowAffected > 0 && rowAffected1 == order.getProductList().size()){
                System.out.println("Product has been ordered!");
            }else {
                System.out.println("Product out of stock!");
            }
            return rowAffected;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return 0;
        }
    }

    @Override
    public int updateOrderById(Integer id) {
        String sql = "UPDATE \"order\" SET order_name = ?, order_description = ? WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Order order = getOrderById(id);
            if (order != null) {
                System.out.print("[+] Insert order name: ");
                preparedStatement.setString(1, new Scanner(System.in).next());
                System.out.print("[+] Insert order description: ");
                preparedStatement.setString(2, new Scanner(System.in).next());
                preparedStatement.setInt(3, id);
                int rowAffected = preparedStatement.executeUpdate();
                if (rowAffected > 0) {
                    System.out.println("[+] Update order successfully");
                    return rowAffected;
                }else {
                    System.out.println("[+] Update order failed");
                    return rowAffected;
                }
            }else{
                System.out.println("Not Found! order");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int deleteOrder(Integer id) {
        String sql = "DELETE FROM product_order WHERE order_id = ?";
        String sql1 = "DELETE FROM \"order\" WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             PreparedStatement preparedStatement1 = connection.prepareStatement(sql1)
        ) {
            // Delete from product_order table first
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            // Then delete from order table
            preparedStatement1.setInt(1, id);
            int rowAffected = preparedStatement1.executeUpdate();
            String message = rowAffected>0 ? "[+] Delete order successfully" : "[+] Delete order failed";
            System.out.println(message);
            return rowAffected;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return 0;
        }
    }
}
