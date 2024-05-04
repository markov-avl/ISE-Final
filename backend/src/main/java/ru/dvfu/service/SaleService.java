package ru.dvfu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.dvfu.entity.Sale;
import ru.dvfu.exception.FailedToSortException;
import ru.dvfu.repository.SaleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public Sale getById(Long id) {
        return findById(id).orElseThrow();
    }

    public Optional<Sale> findById(Long id) {
        return saleRepository.findById(id);
    }

    public Page<Sale> getAll(Pageable page) {
        try {
            return saleRepository.findAll(page);
        } catch (PropertyReferenceException exception) {
            throw new FailedToSortException(exception);
        }
    }

}
