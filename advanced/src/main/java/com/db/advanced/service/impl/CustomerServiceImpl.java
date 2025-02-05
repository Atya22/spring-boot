package com.db.advanced.service.impl;

import com.db.advanced.dao.repository.CustomerRepository;
import com.db.advanced.dao.repository.OrderRepository;
import com.db.advanced.dto.CustomerResponseDto;
import com.db.advanced.dto.OrderResponseDto;
import com.db.advanced.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public CustomerResponseDto getCustomer(Long id) {
        var customerEntity = customerRepository.findById(id).orElseThrow();

        var orderEntities = customerEntity.getOrders();
        List<OrderResponseDto> orderDtos = new ArrayList<>();
        for (var order : orderEntities) {
            var orderDto = OrderResponseDto.builder()
                    .name(order.getName())
                    .count(order.getCount())
                    .build();
            orderDtos.add(orderDto);
        }

        var customerResponseDto = CustomerResponseDto.builder()
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .orders(orderDtos)
                .build();

        return customerResponseDto;
    }
}
