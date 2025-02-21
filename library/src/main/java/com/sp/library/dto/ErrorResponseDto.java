package com.sp.library.dto;

import com.sp.library.util.enums.CustomResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponseDto {
    private CustomResponseStatus status;
    private String message;
}
