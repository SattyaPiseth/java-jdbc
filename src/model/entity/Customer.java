package model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author Sattya
 * create at 6/15/2024 12:41 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean isDeleted;
    private Date createdDate;

}
