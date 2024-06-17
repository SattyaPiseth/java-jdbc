package model.dto;

import lombok.Builder;

/**
 * @author Sattya
 * create at 6/17/2024 2:46 AM
 */
@Builder
public record OrderDto(String order_name, String order_description, String customer_name,String customer_email,String ordered_at) {
}
