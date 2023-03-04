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
public class Applicant {

    @Id
    @Column(unique = true)
    private Long id;

    @Column(nullable = true)
    private Integer age;

    @Column(name = "Role",nullable = true)
    private String role;

    @Column(name = "Rank",nullable = true)
    private String rank;

    @Column(nullable = true)
    private Boolean isAccepted;


    @Column(nullable = true)
    private String valoTrackerReference;

    public Applicant(Long id){
        this.id = id;
    }

}
