package ru.dvfu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dvfu.dto.GenreDto;
import ru.dvfu.dto.PageDto;
import ru.dvfu.dto.params.PageParamsDto;
import ru.dvfu.dto.params.SortParamsDto;
import ru.dvfu.entity.Genre;
import ru.dvfu.mapper.GenreMapper;
import ru.dvfu.service.GenreService;
import ru.dvfu.util.PageUtil;
import ru.dvfu.util.SortUtil;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    private final GenreMapper genreMapper;

    @GetMapping
    public ResponseEntity<PageDto<GenreDto>> getAll(
            @Valid PageParamsDto pageParamsDto,
            @Valid SortParamsDto sortParamsDto
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Sort sort = SortUtil.request(sortParamsDto);

        Page<Genre> genres = genreService.getAll(pageRequest.withSort(sort));
        PageDto<GenreDto> genresDto = genreMapper.toPageDto(genres);

        return ResponseEntity.ok(genresDto);
    }

}
