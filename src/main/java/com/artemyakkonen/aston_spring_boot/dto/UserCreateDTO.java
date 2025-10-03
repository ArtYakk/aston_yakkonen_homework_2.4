package com.artemyakkonen.aston_spring_boot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserCreateDTO {
    @NotBlank
    @Size(max = 15)
    private String name;

    @Email
    @Size(max = 30)
    private String email;

    @NotNull
    @Min(1)
    @Max(150)
    private Integer age;
}
