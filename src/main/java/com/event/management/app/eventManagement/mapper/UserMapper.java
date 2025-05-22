package com.event.management.app.eventManagement.mapper;

import com.event.management.app.eventManagement.dto.UseryDto;
import com.event.management.app.eventManagement.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UseryDto toDto(User user);
    User toEntity(UseryDto dto);
}
