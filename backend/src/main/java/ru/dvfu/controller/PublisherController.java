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
import ru.dvfu.dto.PublisherDto;
import ru.dvfu.dto.params.PageParamsDto;
import ru.dvfu.dto.params.SortParamsDto;
import ru.dvfu.entity.Publisher;
import ru.dvfu.mapper.PublisherMapper;
import ru.dvfu.service.PublisherService;
import ru.dvfu.util.PageUtil;
import ru.dvfu.util.SortUtil;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    private final PublisherMapper publisherMapper;

    @GetMapping
    public ResponseEntity<PageDto<PublisherDto>> getAll(
            @Valid PageParamsDto pageParamsDto,
            @Valid SortParamsDto sortParamsDto
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Sort sort = SortUtil.request(sortParamsDto);

        Page<Publisher> publishers = publisherService.getAll(pageRequest.withSort(sort));
        PageDto<PublisherDto> publishersDto = publisherMapper.toPageDto(publishers);

        return ResponseEntity.ok(publishersDto);
    }

}
