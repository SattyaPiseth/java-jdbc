package model.dao;

import model.entity.Product;

import java.util.List;

/**
 * @author Sattya
 * create at 6/16/2024 1:43 AM
 */
public interface ProductDao {
    Product getProductById(Integer id);
    List<Product> getAllProducts();
    int addNewProduct(Product product);
    int updateProduct(Integer id);
    int deleteProduct(Integer id);
}
