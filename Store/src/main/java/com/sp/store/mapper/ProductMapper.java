package com.sp.store.mapper;

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

    public List<ProductDto> entityToDto() {
        List<ProductDto> dtos = new ArrayList<>();
        for (var entity : productRepository.findAll()) {
            var dto = ProductDto.builder()
                    .name(entity.getName())
                    .price(entity.getPrice())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }
}
