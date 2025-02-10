package com.db.advanced.service;

import com.db.advanced.dto.ClientProductResponseDto;

import java.util.List;

public interface ClientProductService {
    List<ClientProductResponseDto> getProducts();
}
