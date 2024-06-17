package service;

import model.Mapper.ProductMapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 12:42 AM
 */
public class ProductServiceImpl implements ProductService{
    private final ProductDao productDao = new ProductDaoImpl();


    @Override
    public List<ProductDto> getAll() {
        return productDao.getAllProducts().stream()
                .map(ProductMapper::fromProductToProductDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(Integer id) {
        return ProductMapper.fromProductToProductDto(productDao.getProductById(id));
    }

    @Override
    public int addNewProduct(ProductDto productDto) {
        return productDao.addNewProduct(ProductMapper.fromProductDtoToProduct(productDto));
    }

    @Override
    public int updateProductById(Integer id) {
        return productDao.updateProduct(id);
    }

    @Override
    public int deleteProductById(Integer id) {
        return productDao.deleteProduct(id);
    }
}
