package com.sp.store.service;


import com.sp.store.dao.entity.ProductEntity;
import com.sp.store.dao.repository.ProductRepository;
import com.sp.store.dto.ProductDto;
import com.sp.store.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Service
@Builder
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void addProduct(ProductDto dto) {
        var entity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
        productRepository.save(entity);
    }

    public ProductDto getProduct(Long id) {
        var entity = productRepository.findById(id);
        var dto = ProductDto.builder()
                .name(entity.get().getName())
                .price(entity.get().getPrice())
                .build();
        return dto;
    }

    public List<ProductDto> getProducts() {
        return productMapper.entityToDto();
    }

    public void updateProduct(ProductDto dto, Long id) {
        ProductEntity entity = productRepository.findById(id).orElse(null);

        if (entity != null) {
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            productRepository.save(entity);
        }
    }
}