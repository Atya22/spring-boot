package com.sp.store.service.impl;

import com.sp.store.dao.entity.ProductEntity;
import com.sp.store.dao.repository.ProductRepository;
import com.sp.store.dto.ProductDto;
import com.sp.store.mapper.ProductMapper;
import com.sp.store.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
@AllArgsConstructor

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void addProduct(ProductDto dto) {
        var entity = ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .categoryId(dto.getCategoryId())
                .build();
        productRepository.save(entity);
    }

    public ProductDto getProduct(Long id) {
        var entity = productRepository.findById(id);
        var dto = ProductDto.builder()
                .name(entity.get().getName())
                .price(entity.get().getPrice())
                .quantity(entity.get().getQuantity())
                .categoryId(entity.get().getCategoryId())
                .build();
        return dto;
    }

    public List<ProductDto> getProducts() {
        return productMapper.entityListToDtoList();
    }

    public void updateProduct(ProductDto dto, Long id) {
        ProductEntity entity = productRepository.findById(id).orElse(null);

        if (entity != null) {
            if (dto.getName() != null) entity.setName(dto.getName());
            if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
            if (dto.getQuantity() != null) entity.setQuantity(dto.getQuantity());
            if (dto.getCategoryId() != null) entity.setCategoryId(dto.getCategoryId());

            productRepository.save(entity);
        }
    }

    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        }
    }
}
