package com.xxavierr404.digigame.dto.mapper;

import com.xxavierr404.digigame.domain.License;
import com.xxavierr404.digigame.dto.LicenseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LicenseMapper {
    @Mapping(target = "userId", source = "license.user.id")
    @Mapping(target = "gameId", source = "game.id")
    LicenseDto toDto(License license);
}
