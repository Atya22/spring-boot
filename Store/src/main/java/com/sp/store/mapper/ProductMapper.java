package com.sp.store.mapper;

import com.sp.store.dao.entity.ProductEntity;
import com.sp.store.dao.repository.ProductRepository;
import com.sp.store.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductMapper {
    private final ProductRepository productRepository;


    public ProductDto entityToDto(ProductEntity entity) {
        var dto = ProductDto.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();

        return dto;
    }

    public List<ProductDto> entityListToDtoList() {
        List<ProductDto> dtos = new ArrayList<>();
        for (var entity : productRepository.findAll()) {
            var dto = ProductDto.builder()
                    .name(entity.getName())
                    .price(entity.getPrice())
                    .quantity(entity.getQuantity())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    public List<ProductDto> entityListToDtoList(List<ProductEntity> entities) {
        List<ProductDto> dtos = new ArrayList<>();
        for (var entity : entities) {
            var dto = ProductDto.builder();
            if (entity.getName() != null) {
                dto.name(entity.getName());
            }
            if (entity.getPrice() != null) {
                dto.price(entity.getPrice());
            }
            if (entity.getQuantity() != null) {
                dto.quantity(entity.getQuantity());
            }
            if (entity.getCategoryId() != null) {
                dto.categoryId(entity.getCategoryId());
            }
            dtos.add(dto.build());
        }
        return dtos;
    }

}
