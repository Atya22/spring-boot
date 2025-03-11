package com.sp.library.controller;

import com.sp.library.dto.UserRequestDto;
import com.sp.library.util.enums.Gender;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/redis")

public class RedisApiController {
    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public void setToRedis() {
        redisTemplate.opsForValue()
                .set("users", new UserRequestDto("Jey", "Leo", LocalDate.parse("2010-10-05"), Gender.MAN,true ));
    }

}
