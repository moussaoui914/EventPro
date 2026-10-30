package org.example.eventproo.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter @NoArgsConstructor
public class Organisateur extends User { private String nomOrganisation; }