package com.db.advanced.service.impl;

import com.db.advanced.client.ProductClient;
import com.db.advanced.dto.ClientProductResponseDto;
import com.db.advanced.service.ClientProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientProductServiceImpl implements ClientProductService {
    private final ProductClient productClient;

    @Override
    public List<ClientProductResponseDto> getProducts(){
        return productClient.getProducts();
    }
}
