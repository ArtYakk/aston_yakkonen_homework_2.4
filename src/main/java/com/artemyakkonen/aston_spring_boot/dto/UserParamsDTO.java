package com.artemyakkonen.aston_spring_boot.dto;

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

    private Integer ageGt;

    private Integer ageLt;

    private LocalDateTime createdAtGt;

    private LocalDateTime createdAtLt;

    private Integer page = 0;
    private Integer size = 20;
    private String sortBy = "createdAt";
    private String sortDirection = "desc";

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
    }
}
