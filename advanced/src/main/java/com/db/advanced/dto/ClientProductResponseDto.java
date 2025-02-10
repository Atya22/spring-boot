package com.db.advanced.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientProductResponseDto {
    @NotBlank(message = "name is mandatory")
    private String name;
    @Max(100000)
    private Double price;
    @Min(1)
    private Integer quantity;
    private Long categoryId;
}
