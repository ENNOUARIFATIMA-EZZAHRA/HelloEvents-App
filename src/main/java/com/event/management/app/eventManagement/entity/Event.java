package com.event.management.app.eventManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nom;
  private String date;
  private String lieu;
  private String category;
  private String password;
  private String location;
  @Column(length = 500)
  private String descriptionCourte;

  private String imageUrl;
}
