package ru.dvfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
