package ru.dvfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
