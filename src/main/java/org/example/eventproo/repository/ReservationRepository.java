package org.example.eventproo.repository;
import org.example.eventproo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ReservationRepository extends JpaRepository<Reservation, Long> { }