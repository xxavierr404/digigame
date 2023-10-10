package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.User;
import com.xxavierr404.digigame.dto.UserCredentialsDto;
import com.xxavierr404.digigame.dto.UserRegisterDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserCredentialsDto toCredentialsDto(User user);
    UserRegisterDto toUserRegisterDto(User user);
    User userRegisterDtoToEntity(UserRegisterDto dto);
}
