package org.example.eventproo.repository;
import jakarta.persistence.LockModeType;
import org.example.eventproo.entity.CategorieBillet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieBilletRepository extends JpaRepository<CategorieBillet, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM CategorieBillet c WHERE c.id = :id")
    CategorieBillet findByIdWithLock(@Param("id") Long id);
}