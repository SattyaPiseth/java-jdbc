package model.dto;

import lombok.Builder;

/**
 * @author Sattya
 * create at 6/17/2024 2:22 AM
 */
@Builder
public record ProductDto(String productName, String productCode, String productDescription,String imported_at,String expired_at) {
}
