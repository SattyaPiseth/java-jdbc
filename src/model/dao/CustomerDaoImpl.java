package model.dao;

import connection.ConnectionPool;
import model.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sattya
 * create at 6/16/2024 2:09 PM
 */
public class CustomerDaoImpl implements CustomerDao{
    @Override
    public List<Customer> queryAllCustomers() {
        String sql = """
        SELECT * FROM "customer"
        """;
        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setIsDeleted(resultSet.getBoolean("is_deleted"));
                customer.setCreatedDate(resultSet.getDate("created_date"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public int updateCustomerById(Integer id) {
        Customer customer = queryCustomerById(id);
        if (customer == null){
            System.out.println("Customer not found!");
            return 0;
        }
        String sql = """
                UPDATE "customer"
                SET name =?, email =? WHERE id =?
                """;
        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.print("[+] Enter new name: ");
            preparedStatement.setString(1,new Scanner(System.in).next());
            System.out.print("[+] Enter new email: ");
            preparedStatement.setString(2,new Scanner(System.in).next());
            preparedStatement.setInt(3,id);
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected > 0? "[+] Customer has been updated successfully!" : "[+] Customer has not been updated!" ;
            System.out.println(message);
            return rowAffected;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }



    @Override
    public int deleteCustomerById(Integer id) {
        String deleteProductOrdersSql = "DELETE FROM \"product_order\" WHERE order_id IN (SELECT id FROM \"order\" WHERE cus_id = ?)";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement deleteProductOrdersStmt = connection.prepareStatement(deleteProductOrdersSql)) {
            deleteProductOrdersStmt.setInt(1, id);
            deleteProductOrdersStmt.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return 0;
        }

        String deleteOrdersSql = "DELETE FROM \"order\" WHERE cus_id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement deleteOrdersStmt = connection.prepareStatement(deleteOrdersSql)) {
            deleteOrdersStmt.setInt(1, id);
            deleteOrdersStmt.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return 0;
        }

        String sql = "DELETE FROM customer WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected > 0? "[+] Customer has been deleted successfully!" : "[+] Invalid customer!" ;
            System.out.println(message);
            return rowAffected;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return 0;
        }
    }

    @Override
    public int addNewCustomer(Customer customer) {
        String sql = """
                INSERT INTO customer (name, email, password, is_deleted, created_date)
                VALUES (?,?,?,?,?)
                """;
        try(Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setBoolean(4, customer.getIsDeleted());
            preparedStatement.setDate(5, customer.getCreatedDate());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected>0){
                System.out.println("Customer has been added successfully!");
            }
            return rowAffected;
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Customer> searchCustomerByName(String name) {
        String sql = "SELECT * FROM customer WHERE name LIKE ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setIsDeleted(resultSet.getBoolean("is_deleted"));
                customer.setCreatedDate(resultSet.getDate("created_date"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Customer queryCustomerById(Integer id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));
                customer.setIsDeleted(resultSet.getBoolean("is_deleted"));
                customer.setCreatedDate(resultSet.getDate("created_date"));
                return customer;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }
}
