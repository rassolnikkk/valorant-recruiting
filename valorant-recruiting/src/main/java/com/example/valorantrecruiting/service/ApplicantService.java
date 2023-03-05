package com.example.valorantrecruiting.service;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.repository.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ApplicantService {

    ApplicantRepo applicantRepo;

    public List<Applicant> getALlApplicants() {
        return applicantRepo.findAll();
    }

    public boolean applicantExistsById(Long id) {
        return applicantRepo.existsById(id);
    }

    public Optional<Applicant> findApplicantById(Long id) {
        return applicantRepo.findById(id);
    }

    public void addApplicant(Applicant applicant) {
        applicantRepo.save(applicant);
    }

    public void deleteApplicantsByIds(List<Long> idsToDelete){
        applicantRepo.deleteAllById(idsToDelete);
    }

    public void deleteApplicant(Applicant applicant){
        applicantRepo.delete(applicant);
    }


}
