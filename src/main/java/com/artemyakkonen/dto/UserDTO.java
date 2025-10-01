package com.artemyakkonen.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDTO {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDateTime createdAt;
}
