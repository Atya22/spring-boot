package com.db.advanced.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponseDto {
    private String name;
    private String email;
    List<OrderResponseDto> orders;
}
