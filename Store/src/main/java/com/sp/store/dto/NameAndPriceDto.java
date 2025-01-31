package com.sp.store.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NameAndPriceDto {
    private String name;
    private Double price;

}
