package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.UserCredentialsDto;
import com.xxavierr404.digigame.dto.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    UserCredentialsDto toCredentialsDto(User user);
    UserRegisterDto toUserRegisterDto(User user);
    @Mapping(source = "dto.nickname", target = "nickname")
    @Mapping(source = "dto.username", target = "username")
    @Mapping(source = "dto.password", target = "password")
    @Mapping(source = "dto.role", target = "role")
    User userRegisterDtoToEntity(UserRegisterDto dto);
}
