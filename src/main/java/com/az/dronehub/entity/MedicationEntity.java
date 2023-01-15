package com.az.dronehub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medication")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private String weight;

    @Column(name = "code")
    private String code;

    // Store image not in database,
    // but in third party resource system and in DB store just URL
    @Column(name = "image")
    private String image;
}
