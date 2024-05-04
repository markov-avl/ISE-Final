package ru.dvfu.mapper;

import org.mapstruct.Mapper;
import ru.dvfu.dto.GenreDto;
import ru.dvfu.entity.Genre;

@Mapper(componentModel = "spring")
public abstract class GenreMapper implements CommonMapper<Genre, GenreDto> {
}
