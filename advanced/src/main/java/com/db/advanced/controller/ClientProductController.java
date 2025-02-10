package com.db.advanced.controller;


import com.db.advanced.client.ProductClient;
import com.db.advanced.dto.ClientProductResponseDto;
import com.db.advanced.service.ClientProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ClientProductController {
    private final ClientProductService clientProductService;
    @GetMapping
    List<ClientProductResponseDto> getProducts(){
       return clientProductService.getProducts();
    }
}
