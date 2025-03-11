package com.sp.library.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.library.exception.UserNotFoundException;
import com.sp.library.exception.UserRegisteredBeforeException;
import com.sp.library.mapper.UserCustomMapper;
import com.sp.library.dao.entity.UserEntity;
import com.sp.library.dao.repository.UserRepository;
import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import com.sp.library.service.RedisService;
import com.sp.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCustomMapper userCustomMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final RedisService redisService;


    @Override
    public void addUser(UserRequestDto dto) {
        if (userRepository.findByLname(dto.getLname()).isPresent()) {
            throw new UserRegisteredBeforeException("lname used before");
        }
        var entity = UserEntity.builder()
                .fname(dto.getFname())
                .lname(dto.getLname())
                .bday(dto.getBday())
                .gender(dto.getGender())
                .build();
        userRepository.save(entity);

        redisTemplate.delete("users");
    }


//    public List<UserResponseDto> getUsers() {
//        try {
//            return redisService.getListFromCache("users");
//        }catch (Exception e){
//            System.err.println("Error while deserializing cached data: " + e.getMessage());
//           var userEntityList =  userRepository.findAll(); // Fallback in case of deserialization failure
//            return userCustomMapper.entityListToDtoList(userEntityList);
//        }
//    }

    @Cacheable(value = "users")
    public List<UserResponseDto> getUsers() {
        var data = userRepository.findAll();
        return userCustomMapper.entityListToDtoList(data);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        var optionalUserEntity = userRepository.findById(id);
        var userEntity = UserEntity.builder().build();
        if (optionalUserEntity.isPresent()) {
            userEntity = optionalUserEntity.get();
        }
        return userCustomMapper.entityToDto(userEntity);
    }

    @Override
    public Long updateUser(Long id, UserRequestDto dto) {
        var user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with given id: {} " + id.toString())
                );
        if (dto.getBday() != null)
            user.setBday(dto.getBday());
        if (dto.getFname() != null)
            user.setFname(dto.getFname());
        if (dto.getLname() != null)
            user.setLname(dto.getLname());
        if (dto.getGender() != null)
            user.setGender(dto.getGender());

        userRepository.save(user);

        redisTemplate.delete(String.valueOf(id));
        return id;
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteUsers() {
        userRepository.deleteAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteCache() {

    }


}
