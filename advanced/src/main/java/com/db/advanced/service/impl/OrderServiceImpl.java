package com.db.advanced.service.impl;


import com.db.advanced.dao.repository.CustomerRepository;
import com.db.advanced.dao.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
}
