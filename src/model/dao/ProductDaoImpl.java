package model.dao;

import connection.ConnectionPool;
import model.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Sattya
 * create at 6/16/2024 11:23 PM
 */
public class ProductDaoImpl implements ProductDao{
    @Override
    public Product getProductById(Integer id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("product_description"));
                product.setProductCode(resultSet.getString("product_code"));
                product.setIsDeleted(resultSet.getBoolean("is_deleted"));
                product.setImportedDate(resultSet.getDate("imported_at"));
                product.setExpiratedDate(resultSet.getDate("expired_at"));
                return product;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM product";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setDescription(resultSet.getString("product_description"));
                product.setProductCode(resultSet.getString("product_code"));
                product.setIsDeleted(resultSet.getBoolean("is_deleted"));
                product.setImportedDate(resultSet.getDate("imported_at"));
                product.setExpiratedDate(resultSet.getDate("expired_at"));
                productList.add(product);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return productList;
    }

    @Override
    public int addNewProduct(Product product) {
        String sql = """
                INSERT INTO product (product_name, product_code, product_description, is_deleted, imported_at, expired_at)
                VALUES (?,?,?,?,?,?)
                """;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductCode());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setBoolean(4, product.getIsDeleted());
            preparedStatement.setDate(5, product.getImportedDate());
            preparedStatement.setDate(6, product.getExpiratedDate());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Product has been added successfully!");
            }else {
                System.out.println("Product has not been added successfully!");
            }
            return rowAffected;
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int updateProduct(Integer id) {
        Product product = getProductById(id);
        if (product == null) {
            System.out.println("Product not found!");
            return 0;
        }
        String sql = """
                UPDATE product
                SET product_name =?, product_code =?, product_description =?, is_deleted =?, imported_at =?, expired_at =?
                WHERE id =?
                """;
        try(Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.print("[+] Enter new product name: ");
            preparedStatement.setString(1, new Scanner(System.in).next());
            System.out.print("[+] Enter new product code: ");
            preparedStatement.setString(2, new Scanner(System.in).next());
            System.out.print("[+] Enter new product description: ");
            preparedStatement.setString(3, new Scanner(System.in).next());
            preparedStatement.setBoolean(4, product.getIsDeleted());
            preparedStatement.setDate(5, product.getImportedDate());
            preparedStatement.setDate(6, product.getExpiratedDate());
            preparedStatement.setInt(7, id);
            int rowAffected = preparedStatement.executeUpdate();
            String message = rowAffected > 0? "[+] Product has been updated successfully!" : "[+] Product has not been updated!" ;
            System.out.println(message);
            return rowAffected;

        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteProduct(Integer id) {
        String sqlDeleteProductOrder = "DELETE FROM product_order WHERE pro_id = ?";
        String sqlDeleteProduct = "DELETE FROM product WHERE id = ?";
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatementProductOrder = connection.prepareStatement(sqlDeleteProductOrder);
             PreparedStatement preparedStatementProduct = connection.prepareStatement(sqlDeleteProduct)) {

            // First, delete the corresponding records from the product_order table
            preparedStatementProductOrder.setInt(1, id);
            preparedStatementProductOrder.executeUpdate();

            // Then, delete the product
            preparedStatementProduct.setInt(1, id);
            int rowAffected = preparedStatementProduct.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Product has been deleted successfully!");
            } else {
                System.out.println("Product don't have in stock!");
            }
            return rowAffected;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

}
