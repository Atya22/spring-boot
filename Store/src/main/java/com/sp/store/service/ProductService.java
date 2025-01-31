package com.sp.store.service;


import com.sp.store.dto.ProductDto;

import java.util.List;

public interface ProductService {
     void addProduct(ProductDto dto);

     ProductDto getProduct(Long id);

     List<ProductDto> getProducts();

     void updateProduct(ProductDto dto, Long id);

     void deleteProduct(Long id);
}