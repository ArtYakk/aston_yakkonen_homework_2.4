package com.artemyakkonen.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank
    @Size(max = 15)
    private String name;

    @Email
    @Size(max = 20)
    private String email;

    @NotNull
    @Min(1)
    @Max(150)
    private Integer age;
}
