package org.example.eventproo.repository;
import org.example.eventproo.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    boolean existsByIdempotencyKey(String idempotencyKey);
}