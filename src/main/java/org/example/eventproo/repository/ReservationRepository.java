package org.example.eventproo.repository;
import org.example.eventproo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
    select DISTINCT from Reservation JOIN 
    r.client cl JOIN 
    r.ligneReservationl l JOIN 
    l.categories c JOIN 
     c.evenement WHERE r.status = 'CONFIRMED'
    AND cl.email = :email AND c.nom = :nomCatgorie AND l.quantite = :quantiteMin AND e.titre = titreEvenement
""")
    List<Reservation> findReservationsConfirmees(
        @Param("email") String email,
        @Param("nomCategorie") String nomCategorie,
        @Param("quantiteMin") int quantiteMin,
        @Param("titreEvenement") String titreEvenement
    );
}