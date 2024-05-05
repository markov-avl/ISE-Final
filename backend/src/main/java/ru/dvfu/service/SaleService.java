package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Sale;
import ru.dvfu.exception.FailedToSortException;
import ru.dvfu.model.Filter;
import ru.dvfu.repository.SaleRepository;

import java.util.Map;
import java.util.Optional;

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
            "numberOfSales", "numberOfSales"
    );

    private final SaleRepository saleRepository;

    public Sale getById(Long id) {
        return findById(id).orElseThrow();
    }

    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    @Cacheable
    public Page<Sale> getAll(Pageable page) {
        try {
            return saleRepository.findAll(page);
        } catch (PropertyReferenceException exception) {
            throw new FailedToSortException(exception);
        }
    }

    @Cacheable
    public Page<Sale> getAll(PageRequest page, Filter filter) {
        Sort.Order[] correctOrders = page.getSort().stream()
                .map(order -> {
                    if (SORT_MAPPINGS.containsKey(order.getProperty())) {
                        return order.withProperty(SORT_MAPPINGS.get(order.getProperty()));
                    }
                    throw new FailedToSortException("Неизвестное поле для сортировки: " + order.getProperty());
                })
                .toArray(Sort.Order[]::new);
        Sort correctSort = Sort.by(correctOrders);

        return saleRepository.findByPublishersAndPlatformsAndGenresAndYears(
                filter.getPublishers().isEmpty(),
                filter.getPlatforms().isEmpty(),
                filter.getGenres().isEmpty(),
                filter.getYears().isEmpty(),
                filter.getRegions().isEmpty(),
                filter.getPublishers(),
                filter.getPlatforms(),
                filter.getGenres(),
                filter.getYears(),
                filter.getRegions(),
                page.withSort(correctSort)
        );
    }

}
