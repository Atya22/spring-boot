package com.sp.store.controller;

import com.sp.store.dao.entity.ProductEntity;
import com.sp.store.dao.repository.ProductRepository;
import com.sp.store.dto.ProductDto;
import com.sp.store.service.ProductService;
import lombok.Builder;
import lombok.Data;
import lombok.Lombok;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody ProductDto dto){
        productService.addProduct(dto);
    }

    @GetMapping("{id}")
    public ProductDto getProduct (@PathVariable Long id){
      return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductDto> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("{id}")
    public void updateProduct (@RequestBody ProductDto dto, @PathVariable Long id){
        productService.updateProduct(dto, id);
    }
}
