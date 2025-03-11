package com.sp.library.controller;


import com.sp.library.dto.UserRequestDto;
import com.sp.library.service.RedisService;
import com.sp.library.util.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/redis-test")
public class TestController {
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisService redisService;

    @GetMapping
    public void setToRedis() {
        redisTemplate.opsForValue().set("2045", "apple");
        redisTemplate.opsForValue()
                .set("users", new UserRequestDto("Bob", "Jey", LocalDate.parse("2012-10-05"), Gender.MAN, true));
    }
}
