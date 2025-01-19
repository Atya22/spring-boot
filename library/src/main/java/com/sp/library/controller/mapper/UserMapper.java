package com.sp.library.controller.mapper;

import com.sp.library.dao.entity.UserEntity;
import com.sp.library.dto.UserRequestDto;
import com.sp.library.dto.UserResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
}
