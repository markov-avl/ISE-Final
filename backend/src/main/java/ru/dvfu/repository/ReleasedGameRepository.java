package ru.dvfu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.ReleasedGame;

@Repository
public interface ReleasedGameRepository extends JpaRepository<ReleasedGame, Long> {

    @Query("SELECT DISTINCT rg.year " +
            "FROM ReleasedGame rg " +
            "WHERE rg.year IS NOT NULL")
    Page<Integer> findDistinctYearsNotNull(Pageable pageable);

}
