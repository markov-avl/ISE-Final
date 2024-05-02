package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Platform;
import ru.dvfu.exception.FailedToSortException;
import ru.dvfu.repository.PlatformRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    public Platform getById(Long id) {
        return findById(id).orElseThrow();
    }

    public Optional<Platform> findById(Long id) {
        return platformRepository.findById(id);
    }

    public Page<Platform> getAll(Pageable page) {
        try {
            return platformRepository.findAll(page);
        } catch (PropertyReferenceException exception) {
            throw new FailedToSortException(exception);
        }
    }

}
