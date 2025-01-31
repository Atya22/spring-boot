package com.sp.store.controller;

import com.sp.store.dao.repository.ProductRepository;
import com.sp.store.dto.CategoryProductCountDto;
import com.sp.store.dto.NameAndPriceDto;
import com.sp.store.dao.projection.ProductDiscountProjection;
import com.sp.store.dto.ProductDto;
import com.sp.store.mapper.ProductMapper;
import com.sp.store.service.ProductService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Data
@Builder
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @PostMapping
    public void createProduct(@RequestBody ProductDto dto) {
        productService.addProduct(dto);
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping()
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @PatchMapping("{id}")
    public void updateProduct(@RequestBody ProductDto dto, @PathVariable Long id) {
        productService.updateProduct(dto, id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("price-range")
    public List<ProductDto> getProductsByPriceRange(@RequestParam(value = "price1", required = false) Double price1,
                                                    @RequestParam(value = "price2", required = false) Double price2) {
        return productMapper.entityListToDtoList(productRepository.findByPriceBetweenOrderByName(price1, price2));
    }

    @GetMapping("name-containing")
    public List<ProductDto> getProductsByName(@RequestParam(value = "nameContains", required = false) String nameContains) {
        return productMapper.entityListToDtoList(productRepository.findByNameContaining(nameContains));
    }

    @GetMapping("total-quantity")
    public Integer getTotalProductQuantity() {
        return productRepository.sumQuantity();
    }

    @GetMapping("max-price")
    public ProductDto getProductWithMaxPrice() {
        return productMapper.entityToDto(productRepository.findWithMaxPrice());
    }

    @GetMapping("recent/10")
    public List<ProductDto> getTop10RecentProducts() {
        return productMapper.entityListToDtoList(productRepository.findTop10RecentByCreatedAtDesc());
    }

    @GetMapping("avg-price")
    public Integer getAverageProductPrice() {
        return productRepository.findAveragePrice();
    }

    @GetMapping("/categories/count")
    public List<CategoryProductCountDto> getProductCountByCategory() {
        return productRepository.countByCategory();
    }

    @GetMapping("name-price")
    public List<NameAndPriceDto> getProductsByNameAndPrice() {
        return productRepository.findNamesAndPrices();
    }

    @GetMapping("/created-at")
    public List<ProductDto> getProductsByCreatedAt(@RequestParam(value = "createdAt") LocalDateTime createdAt) {
        return productMapper.entityListToDtoList(productRepository.findByCreatedAt(createdAt));
    }

    @GetMapping("distinct")
    public List<ProductDto> getDistinctProducts() {
        return productRepository.findDistinctNames();
    }

    @GetMapping("/name-containing/price-greater-then")
    public List<ProductDto> getProductsByNameAndPriceGreaterThan(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "price") Double price) {
        return productMapper.entityListToDtoList(productRepository.findByNameAndPriceGreaterThan(name, price));
    }

    @GetMapping("discounts")
    public List<ProductDiscountProjection> getProductsWithDiscountDetails() {
        return productRepository.findWithDiscountDetails();
    }
}
