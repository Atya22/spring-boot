package com.sp.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AccountRequestDto {
    @Size(min = 10, max = 12)
    private String accountNumber;
    @Min(value = 10, message = "minimum 10")
    private Double balance;
}
