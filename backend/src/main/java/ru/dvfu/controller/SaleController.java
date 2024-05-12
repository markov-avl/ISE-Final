package ru.dvfu.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dvfu.dto.ChartDataDto;
import ru.dvfu.dto.PageDto;
import ru.dvfu.dto.SaleDto;
import ru.dvfu.dto.SaleExtendedDto;
import ru.dvfu.dto.params.FilterParamsDto;
import ru.dvfu.dto.params.SortParamsDto;
import ru.dvfu.dto.params.PageParamsDto;
import ru.dvfu.entity.Sale;
import ru.dvfu.enumeration.Aggregator;
import ru.dvfu.enumeration.GroupBy;
import ru.dvfu.mapper.SaleMapper;
import ru.dvfu.model.ChartData;
import ru.dvfu.model.Filter;
import ru.dvfu.service.SaleService;
import ru.dvfu.util.FilterUtil;
import ru.dvfu.util.PageUtil;
import ru.dvfu.util.SortUtil;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    private final SaleMapper saleMapper;

    @GetMapping
    public ResponseEntity<PageDto<SaleDto>> getAll(
            @Valid PageParamsDto pageParamsDto,
            @Valid SortParamsDto sortParamsDto
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Sort sort = SortUtil.request(sortParamsDto);

        Page<Sale> sales = saleService.getAll(pageRequest.withSort(sort));
        PageDto<SaleDto> salesDto = saleMapper.toPageDto(sales);

        return ResponseEntity.ok(salesDto);
    }

    @GetMapping("/extended")
    public ResponseEntity<PageDto<SaleExtendedDto>> getExtendedAll(
            @Valid PageParamsDto pageParamsDto,
            @Valid FilterParamsDto filterParamsDto,
            @Valid SortParamsDto sortParamsDto
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Filter filter = FilterUtil.request(filterParamsDto);
        Sort sort = SortUtil.request(sortParamsDto);

        Page<Sale> sales = saleService.getAll(pageRequest.withSort(sort), filter);
        PageDto<SaleExtendedDto> salesDto = saleMapper.toExtendedPageDto(sales);

        return ResponseEntity.ok(salesDto);
    }

    @GetMapping("/chart/{groupBy}")
    public ResponseEntity<PageDto<ChartDataDto>> getChart(
            @PathVariable GroupBy groupBy,
            @Valid PageParamsDto pageParamsDto,
            @Valid FilterParamsDto filterParamsDto,
            @Valid SortParamsDto sortParamsDto,
            @RequestParam(required = false, defaultValue = "SUM") Aggregator aggregator
    ) {
        PageRequest pageRequest = PageUtil.request(pageParamsDto);
        Filter filter = FilterUtil.request(filterParamsDto);
        Sort sort = SortUtil.request(sortParamsDto);

        Page<ChartData> chartData = saleService.getChart(pageRequest.withSort(sort), filter, aggregator, groupBy);
        PageDto<ChartDataDto> chartDataDto = saleMapper.toChartDataPageDto(chartData);

        return ResponseEntity.ok(chartDataDto);
    }

}
