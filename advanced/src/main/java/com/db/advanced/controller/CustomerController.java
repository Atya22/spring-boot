package com.db.advanced.controller;

import com.db.advanced.dao.entity.CustomerEntity;
import com.db.advanced.dto.CustomerResponseDto;
import com.db.advanced.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("{id}")
    public CustomerResponseDto getCustomerById (@PathVariable Long id){
        return customerService.getCustomer(id);
    }
}
