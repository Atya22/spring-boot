package com.db.advanced.client;

import com.db.advanced.dto.ClientProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient (name = "product",url = "http://localhost:8081/api/v1/")
public interface ProductClient {
    @RequestMapping(method = RequestMethod.GET, value = "/product")
    List<ClientProductResponseDto> getProducts();
}
