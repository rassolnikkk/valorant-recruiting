package com.example.valorantrecruiting.model;

import jakarta.persistence.*;
import lombok.*;
//basically my only pojo class
//represents an applicant
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
    @Column(nullable = false)
    private Long id;

    @Column(nullable = true)
    private Integer age;

    @Column(name = "Role",nullable = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "Rank",nullable = true)
    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Column(nullable = true)
    private Boolean isAccepted;


    @Column(nullable = true)
    private String valoTrackerReference;

    public Applicant(Long id){
        this.id = id;
    }



}
