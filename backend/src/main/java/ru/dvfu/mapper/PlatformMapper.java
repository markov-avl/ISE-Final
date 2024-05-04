package ru.dvfu.mapper;

import org.mapstruct.Mapper;
import ru.dvfu.dto.PlatformDto;
import ru.dvfu.entity.Platform;

@Mapper(componentModel = "spring")
public abstract class PlatformMapper implements CommonMapper<Platform, PlatformDto> {
}
