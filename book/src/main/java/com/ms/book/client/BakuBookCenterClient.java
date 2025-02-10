package com.ms.book.client;

import com.ms.book.dto.AliVeNinoClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "AliVeNino", url = "http://localhost:8081/api/v1/BakuBookCenter")
public interface BakuBookCenterClient {
    @RequestMapping(method = RequestMethod.GET)
    List<AliVeNinoClientDto> getAll();

    @RequestMapping(method = RequestMethod.POST)
    void addBookAliVeNinoClient(@RequestBody AliVeNinoClientDto aliVeNinoClientdto);
}
