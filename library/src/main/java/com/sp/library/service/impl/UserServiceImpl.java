package com.sp.library.service.impl;

import com.sp.library.mapper.UserMapper;
import com.sp.library.dao.entity.UserEntity;
import com.sp.library.dao.repository.UserRepository;
import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import com.sp.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void addUser(UserRequestDto dto) {
        var entity = UserEntity.builder()
                .fname(dto.getFname())
                .lname(dto.getLname())
                .bday(dto.getBday())
                .gender(dto.getGender())
                .build();
        userRepository.save(entity);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        var userEntityList = userRepository.findAll();
        return userMapper.entityListToDtoList(userEntityList);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        var optionalUserEntity = userRepository.findById(id);
        var userEntity = UserEntity.builder().build();
//        we are initializing userEntity variable (creating and empty variable)
        if (optionalUserEntity.isPresent()) {
            userEntity = optionalUserEntity.get();
        }
        return userMapper.entityToDto(userEntity);
    }

    @Override
    public Long updateUser(Long id, UserRequestDto dto) {
        var optionalUserEntity = userRepository.findById(id);
        if (optionalUserEntity.isPresent()) {
            var user = optionalUserEntity.get();
            if (dto.getBday() != null)
                user.setBday(dto.getBday());
            if (dto.getFname() != null)
                user.setFname(dto.getFname());
            if (dto.getLname() != null)
                user.setLname(dto.getLname());
            if (dto.getGender() != null)
                user.setGender(dto.getGender());

            userRepository.save(user);
            return id;
        }
        return 0L;
    }
}
