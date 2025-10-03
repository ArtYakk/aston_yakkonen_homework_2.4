package com.artemyakkonen.aston_spring_boot.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank
    @Size(max = 15)
    private JsonNullable<String> name;

    @Email
    @Size(max = 30)
    private JsonNullable<String> email;

    @NotNull
    @Min(1)
    @Max(150)
    private JsonNullable<Integer> age;
}
