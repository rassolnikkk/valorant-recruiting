package com.example.valorantrecruiting.model;

import jakarta.persistence.*;
import lombok.*;


//entity class which represents an applicant
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "applicant", schema = "recruit_schema")
@Builder
public class Applicant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //this data is always given by discord
    @Column(nullable = false, unique = true)
    private Long discordId;

    @Column(nullable = true)
    private Integer age;

    @Column( nullable = true)
    private String role;

    @Column( nullable = true)
    private String rank;

    @Column(nullable = true)
    private Boolean isAccepted;


    @Column(nullable = true, unique = true)
    private String valorantTrackerLink;

    public Applicant(Long discordId) {
        this.discordId = discordId;
    }
}
