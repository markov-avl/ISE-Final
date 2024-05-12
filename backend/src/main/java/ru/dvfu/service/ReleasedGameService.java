package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.dvfu.enumeration.Region;
import ru.dvfu.repository.ReleasedGameRepository;
import ru.dvfu.util.SortUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReleasedGameService {

    private static final Map<String, String> SORT_MAPPINGS = Map.of(
            "id", "id",
            "region", "region",
            "year", "year"
    );

    private final ReleasedGameRepository releasedGameRepository;

    @Cacheable("years")
    public Page<Integer> getYears(PageRequest page) {
        Pageable pageable = SortUtil.correctSorting(page, SORT_MAPPINGS, List.of("year"));

        return releasedGameRepository.findDistinctYearsNotNull(pageable);
    }

    @Cacheable("regions")
    public Page<Region> getRegions(Pageable page) {
        Comparator<Region> comparator = Optional.ofNullable(page.getSort().getOrderFor("region"))
                .map(Sort.Order::getDirection)
                .orElse(SortUtil.DEFAULT_DIRECTION)
                .isAscending()
                ? Comparator.naturalOrder()
                : Comparator.reverseOrder();

        List<Region> data = Arrays.stream(Region.values())
                .sorted(comparator)
                .skip((long) page.getPageNumber() * page.getPageSize())
                .limit(page.getPageSize())
                .toList();

        return new PageImpl<>(data, page, Region.values().length);
    }

}
