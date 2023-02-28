package com.example.valorantrecruiting.repository;

import com.example.valorantrecruiting.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, Long> {

}
