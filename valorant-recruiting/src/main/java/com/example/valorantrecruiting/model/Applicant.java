package com.example.valorantrecruiting.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

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

}
