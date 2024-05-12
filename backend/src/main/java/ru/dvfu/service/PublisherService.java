package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Publisher;
import ru.dvfu.repository.PublisherRepository;
import ru.dvfu.util.SortUtil;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private static final Map<String, String> SORT_MAPPINGS = Map.of(
            "id", "id",
            "name", "name"
    );

    private final PublisherRepository publisherRepository;

    @Cacheable("publishers")
    public Page<Publisher> getAll(PageRequest page) {
        Pageable pageable = SortUtil.correctSorting(page, SORT_MAPPINGS, List.of("id", "name"));

        return publisherRepository.findAll(pageable);
    }

}
