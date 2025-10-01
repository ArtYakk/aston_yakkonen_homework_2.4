package com.artemyakkonen.mapper;

import com.artemyakkonen.dto.UserCreateDTO;
import com.artemyakkonen.dto.UserDTO;
import com.artemyakkonen.dto.UserUpdateDTO;
import com.artemyakkonen.model.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    User map(UserCreateDTO dto);
    UserDTO map(User product);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User update(UserUpdateDTO dto, @MappingTarget User product);
    List<User> toUsers(List<UserCreateDTO> dto);
    List<UserDTO> fromUsers(List<User> users);
}
