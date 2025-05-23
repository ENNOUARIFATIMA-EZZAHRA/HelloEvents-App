package com.event.management.app.eventManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String date;
    private String lieu;
    private String categorie;
    private String password;

    @Column(length = 500)
    private String descriptionCourte;

    private String imageUrl;
}
