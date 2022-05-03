package com.shopms.product;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

    private Long id;
    private String name;
    private Integer quantity;
}
