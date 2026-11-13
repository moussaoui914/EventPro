package org.example.eventproo.repository;
import org.example.eventproo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client, Long> { }