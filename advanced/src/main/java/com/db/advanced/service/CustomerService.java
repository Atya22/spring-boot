package com.db.advanced.service;

import com.db.advanced.dao.entity.CustomerEntity;
import com.db.advanced.dto.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface CustomerService {
    CustomerResponseDto getCustomer(Long id);

}
