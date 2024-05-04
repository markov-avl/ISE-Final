package ru.dvfu.mapper;

import org.mapstruct.Mapper;
import ru.dvfu.dto.PublisherDto;
import ru.dvfu.entity.Publisher;

@Mapper(componentModel = "spring")
public abstract class PublisherMapper implements CommonMapper<Publisher, PublisherDto> {
}
