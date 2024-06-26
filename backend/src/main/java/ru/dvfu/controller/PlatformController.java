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
import ru.dvfu.dto.PageDto;
import ru.dvfu.dto.PlatformDto;
import ru.dvfu.dto.params.PageParamsDto;
import ru.dvfu.dto.params.SortParamsDto;
import ru.dvfu.entity.Platform;
import ru.dvfu.mapper.PlatformMapper;
import ru.dvfu.service.PlatformService;
import ru.dvfu.util.PageUtil;
import ru.dvfu.util.SortUtil;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/platforms")
public class PlatformController {

    private final PlatformService platformService;

    private final PlatformMapper platformMapper;

    @GetMapping
    public ResponseEntity<PageDto<PlatformDto>> getAll(
            @Valid PageParamsDto pageParamsDto,
            @Valid SortParamsDto sortParamsDto
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Sort sort = SortUtil.request(sortParamsDto);

        Page<Platform> platforms = platformService.getAll(pageRequest.withSort(sort));
        PageDto<PlatformDto> platformsDto = platformMapper.toPageDto(platforms);

        return ResponseEntity.ok(platformsDto);
    }

}
