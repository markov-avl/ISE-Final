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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dvfu.dto.PageDto;
import ru.dvfu.dto.params.PageParamsDto;
import ru.dvfu.enumeration.Region;
import ru.dvfu.mapper.ReleasedGameMapper;
import ru.dvfu.service.ReleasedGameService;
import ru.dvfu.util.PageUtil;
import ru.dvfu.util.SortUtil;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/released-games")
public class ReleasedGameController {

    private final ReleasedGameService releasedGameService;

    private final ReleasedGameMapper releasedGameMapper;

    @GetMapping("/years")
    public ResponseEntity<PageDto<Integer>> getYears(
            @Valid PageParamsDto pageParamsDto,
            @RequestParam(required = false) Sort.Direction sortDirection
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Sort sort = SortUtil.request(sortDirection, "year");

        Page<Integer> years = releasedGameService.getYears(pageRequest.withSort(sort));
        PageDto<Integer> yearsDto = releasedGameMapper.toYearsPageDto(years);

        return ResponseEntity.ok(yearsDto);
    }

    @GetMapping("/regions")
    public ResponseEntity<PageDto<String>> getRegions(
            @Valid PageParamsDto pageParamsDto,
            @RequestParam(required = false) Sort.Direction sortDirection
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Sort sort = SortUtil.request(sortDirection, "region");

        Page<Region> regions = releasedGameService.getRegions(pageRequest.withSort(sort));
        PageDto<String> regionsDto = releasedGameMapper.toRegionsPageDto(regions);

        return ResponseEntity.ok(regionsDto);
    }

}
