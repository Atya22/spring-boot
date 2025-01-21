package com.sp.library.mapper;

import com.sp.library.dao.entity.UserEntity;
import com.sp.library.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {
    public List<UserResponseDto> entityListToDtoList(List<UserEntity> enityList) {
        List<UserResponseDto> dtos = new ArrayList<>();
        for (UserEntity e : enityList) {
            var dto = UserResponseDto.builder()
                    .isActive(e.getIsActive())
                    .fname(e.getFname())
                    .lname(e.getLname())
                    .gender(e.getGender())
                    .bday(e.getBday())
                    .build();
            dtos.add(dto);
        }
        return dtos;
    }

    public UserResponseDto entityToDto(UserEntity userEntity){
        return UserResponseDto.builder()
                .bday(userEntity.getBday())
                .fname(userEntity.getFname())
                .lname(userEntity.getLname())
                .gender(userEntity.getGender())
                .isActive(userEntity.getIsActive())
                .build();
    }
}
