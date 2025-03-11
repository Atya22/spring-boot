package com.sp.library.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.library.dao.entity.UserEntity;
import com.sp.library.dao.repository.UserRepository;
import com.sp.library.dto.UserResponseDto;
import com.sp.library.mapper.UserCustomMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final UserCustomMapper userMapper;

    public void saveToCache(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object getFromCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void removeFromCache(String key) {
        redisTemplate.delete(key);
    }

    public List<UserEntity> cacheAndGetUsersList() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) throw new RuntimeException();

        try {
            String usersJson = objectMapper.writeValueAsString(users); // Convert list to JSON
            redisTemplate.opsForValue().set("users", usersJson); // Store in Redis
            System.out.println("Users cached successfully!");
            return users;
        } catch (JsonProcessingException e) {
            System.err.println("Error serializing users list: " + e.getMessage());
            throw new RuntimeException();
        }
    }

    public List<UserResponseDto> getListFromCache(String key) throws JsonProcessingException {
        String cachedData = (String) redisTemplate.opsForValue().get(key);
        if (cachedData == null) {
            var users = cacheAndGetUsersList();
            return userMapper.entityListToDtoList(users);
        }

        // Deserialize JSON back to List<UserEntity>
        var usersEntity = objectMapper.readValue(cachedData, new TypeReference<List<UserEntity>>() {
        });
        return userMapper.entityListToDtoList(usersEntity);

    }
}
