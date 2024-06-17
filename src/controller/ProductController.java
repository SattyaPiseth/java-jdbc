package controller;

import model.dto.ProductDto;
import service.ProductService;
import service.ProductServiceImpl;

import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 2:21 AM
 */
public class ProductController {
    private final ProductService productService = new ProductServiceImpl();
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }
    public ProductDto getProductById(Integer id) {
        return productService.getProductById(id);
    }
    public int addNewProduct(ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }
    public int updateProductById(Integer id) {
        return productService.updateProductById(id);
    }
    public int deleteProductById(Integer id) {
        return productService.deleteProductById(id);
    }
}
