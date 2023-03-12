package com.example.valorantrecruiting.repository;

import com.example.valorantrecruiting.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update recruit_schema.applicant set rank = ?2 where id = ?1", nativeQuery = true)
    void updateApplicantRankById(@Param("id") Long id, @Param("rank") String rank);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update recruit_schema.applicant set role = ?2 where id = ?1", nativeQuery = true)
    void updateApplicantRoleById(@Param("id") Long id, @Param("role") String role);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update recruit_schema.applicant set age = ?2 where id = ?1", nativeQuery = true)
    void updateApplicantAgeById(Long id, Integer age);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update recruit_schema.applicant set valo_tracker_reference = ?2 where id = ?1", nativeQuery = true)
    void updateApplicantLinkById(Long id, String valoTrackerLink);
}
