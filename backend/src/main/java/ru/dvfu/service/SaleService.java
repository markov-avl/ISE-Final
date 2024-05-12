package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Sale;
import ru.dvfu.enumeration.Aggregator;
import ru.dvfu.enumeration.GroupBy;
import ru.dvfu.model.ChartData;
import ru.dvfu.model.Filter;
import ru.dvfu.repository.SaleRepository;
import ru.dvfu.util.SortUtil;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SaleService {

    private static final Map<String, String> SORT_MAPPINGS = Map.of(
            "id", "id",
            "name", "releasedGame.game.name",
            "year", "releasedGame.year",
            "platform", "releasedGame.platform.name",
            "genre", "releasedGame.game.genre.name",
            "publisher", "releasedGame.game.publisher.name",
            "region", "region",
            "numberOfSales", "numberOfSales",
            "releasedGameId", "releasedGame.id"
    );

    private final SaleRepository saleRepository;

    @Cacheable("sales")
    public Page<Sale> getAll(PageRequest page) {
        Pageable pageable = SortUtil.correctSorting(
                page,
                SORT_MAPPINGS,
                List.of("id", "releasedGameId", "region", "numberOfSales")
        );

        return saleRepository.findAll(pageable);
    }

    @Cacheable("salesFiltered")
    public Page<Sale> getAll(PageRequest page, Filter filter) {
        Pageable pageable = SortUtil.correctSorting(
                page,
                SORT_MAPPINGS,
                List.of("id", "name", "year", "platform", "genre", "publisher", "region", "numberOfSales")
        );

        return saleRepository.findByPublishersAndPlatformsAndGenresAndYearsAndRegions(
                filter.getPublishers(),
                filter.getPlatforms(),
                filter.getGenres(),
                filter.getYears(),
                filter.getRegions(),
                pageable
        );
    }

    @Cacheable("salesChart")
    public Page<ChartData> getChart(PageRequest page, Filter filter, Aggregator aggregator, GroupBy groupBy) {
        Pageable pageable = SortUtil.correctSorting(
                page,
                SORT_MAPPINGS,
                List.of("year", "numberOfSales", aggregator.name().toLowerCase())
        );

        if (groupBy.equals(GroupBy.PUBLISHER)) {
            return saleRepository.findByPublishersAndPlatformsAndGenresAndYearsAndRegionsGroupByPublisher(
                    aggregator.name(),
                    filter.getPublishers(),
                    filter.getPlatforms(),
                    filter.getGenres(),
                    filter.getYears(),
                    filter.getRegions(),
                    pageable
            );
        } else if (groupBy.equals(GroupBy.PLATFORM)) {
            return saleRepository.findByPublishersAndPlatformsAndGenresAndYearsAndRegionsGroupByPlatform(
                    aggregator.name(),
                    filter.getPublishers(),
                    filter.getPlatforms(),
                    filter.getGenres(),
                    filter.getYears(),
                    filter.getRegions(),
                    pageable
            );
        } else {
            return saleRepository.findByPublishersAndPlatformsAndGenresAndYearsAndRegionsGroupByGenre(
                    aggregator.name(),
                    filter.getPublishers(),
                    filter.getPlatforms(),
                    filter.getGenres(),
                    filter.getYears(),
                    filter.getRegions(),
                    pageable
            );
        }
    }

}
