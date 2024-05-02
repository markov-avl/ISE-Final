package ru.dvfu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvfu.entity.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
