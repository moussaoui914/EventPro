package org.example.eventproo.repository;
import org.example.eventproo.entity.Billet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface BilletRepository extends JpaRepository<Billet, Long> {
    Optional<Billet> findByCode(String code);
}