package com.sp.library.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@SuperBuilder
@Data
public class UserResponseDto extends UserRequestDto {
    private Boolean isActive;
}
