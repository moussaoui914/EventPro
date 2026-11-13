package org.example.eventproo.repository;
import org.example.eventproo.entity.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EvenementRepository extends JpaRepository<Evenement, Long> { }