package model.Mapper;

import model.dto.ProductDto;
import model.entity.Product;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author Sattya
 * create at 6/17/2024 2:25 AM
 */
public class ProductMapper {
    public static ProductDto fromProductToProductDto(Product product){
        if (product == null){
            return null;
        }
        return ProductDto.builder()
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .productDescription(product.getDescription())
                .imported_at(String.valueOf(Date.valueOf(LocalDate.now())))
                .expired_at(String.valueOf(Date.valueOf(LocalDate.now())))
                .build();
    }
    public static Product fromProductDtoToProduct(ProductDto productDto){
        if (productDto == null){
            return null;
        }
        return Product.builder()
               .productName(productDto.productName())
               .productCode(productDto.productCode())
               .description(productDto.productDescription())
                .isDeleted(false)
               .importedDate(Date.valueOf(LocalDate.now()))
               .expiratedDate(Date.valueOf(LocalDate.now()))
               .build();
    }
}
