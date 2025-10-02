package com.artemyakkonen.aston_spring_boot.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank
    @Size(max = 15)
    private JsonNullable<String> name;

    @Email
    @Size(max = 20)
    private JsonNullable<String> email;

    @NotNull
    @Min(1)
    @Max(150)
    private JsonNullable<Integer> age;
}
