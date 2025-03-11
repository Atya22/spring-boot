package com.sp.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountRequestDto {
    @Size(min = 10, max = 12)
    private String accountNumber;
    private Double balance;
}
