package com.sp.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sp.library.util.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String fname;
    @Pattern(regexp = "^.{2,15}$")
    private String lname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate bday;
    private Gender gender;
    private Boolean isActive;
}
