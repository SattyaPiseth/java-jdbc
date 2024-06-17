package service;

import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 12:18 AM
 */
public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getProductById(Integer id);
    int addNewProduct(ProductDto productDto);
    int updateProductById(Integer id);
    int deleteProductById(Integer id);
}
