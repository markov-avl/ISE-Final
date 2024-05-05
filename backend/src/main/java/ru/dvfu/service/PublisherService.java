package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Publisher;
import ru.dvfu.exception.FailedToSortException;
import ru.dvfu.repository.PublisherRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public Publisher getById(Long id) {
        return findById(id).orElseThrow();
    }

    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Cacheable
    public Page<Publisher> getAll(Pageable page) {
        try {
            return publisherRepository.findAll(page);
        } catch (PropertyReferenceException exception) {
            throw new FailedToSortException(exception);
        }
    }

}
