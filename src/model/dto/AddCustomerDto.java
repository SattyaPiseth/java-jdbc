package model.dto;

import lombok.Builder;

/**
 * @author Sattya
 * create at 6/17/2024 2:12 AM
 */
@Builder
public record AddCustomerDto(String name, String email, String password) {
}
