package com.artemyakkonen.aston_spring_boot.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserParamsDTO {
    private String name;

    @Positive
    private Integer ageGt;

    @Positive
    private Integer ageLt;

    private LocalDateTime createdAtGt;

    private LocalDateTime createdAtLt;


    private Integer page = 0;

    private Integer size = 20;

    @Pattern(regexp = "id|createdAt|name|age|email",
            message = "SortBy must be one of: id, createdAt, name, age, email")
    private String sortBy = "createdAt";

    @Pattern(regexp = "asc|desc", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "SortDirection must be 'asc' or 'desc'")
    private String sortDirection = "desc";

    public Pageable toPageable() {
        return PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
    }
}
