package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.UserContent;
import com.xxavierr404.digigame.dto.UserContentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserContentMapper {
    @Mapping(source = "user.id", target = "userId")
    UserContentDto toDto(UserContent content);
}
