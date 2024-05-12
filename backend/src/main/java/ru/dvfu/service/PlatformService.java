package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Platform;
import ru.dvfu.repository.PlatformRepository;
import ru.dvfu.util.SortUtil;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private static final Map<String, String> SORT_MAPPINGS = Map.of(
            "id", "id",
            "name", "name"
    );

    private final PlatformRepository platformRepository;

    @Cacheable("platforms")
    public Page<Platform> getAll(PageRequest page) {
        Pageable pageable = SortUtil.correctSorting(page, SORT_MAPPINGS, List.of("id", "name"));

        return platformRepository.findAll(pageable);
    }

}
