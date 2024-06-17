package model.dto;

import lombok.Builder;

import java.util.List;

/**
 * @author Sattya
 * create at 6/17/2024 2:59 AM
 */
@Builder
public record AddOrderDto(int order_id, String order_name, String order_description, int cus_id, String order_at, List<Integer> pro_id) {
}
