package com.sp.store.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private Double price;
}
