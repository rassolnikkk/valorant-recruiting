package com.example.valorantrecruiting.service;

import com.example.valorantrecruiting.model.Applicant;

import com.example.valorantrecruiting.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepo;

    public Optional<Applicant> findApplicantById(Long id){
        return applicantRepo.findById(id);
    }
    public List<Applicant> getAllApplicants() {
        return applicantRepo.findAll();
    }

    public boolean applicantExistsById(Long id) {
        return applicantRepo.existsById(id);
    }


    public void addApplicant(Applicant applicant) {
        applicantRepo.save(applicant);
    }

    public void deleteApplicantsByIds(List<Long> idsToDelete) {
        applicantRepo.deleteAllById(idsToDelete);
    }

    public void deleteApplicant(Applicant applicant) {
        applicantRepo.delete(applicant);
    }

    public Applicant acceptApplicant(Long id) throws NullPointerException {
        Optional<Applicant> applicantOptional = applicantRepo.findById(id);
        if (applicantOptional.isEmpty()) {
            throw new NullPointerException();
        }
        Applicant applicant = applicantOptional.get();
        applicant.setIsAccepted(true);
        addApplicant(applicant);
        return applicant;
    }

    public Applicant declineApplicant(Long id) throws NullPointerException {
        Optional<Applicant> applicantOptional = applicantRepo.findById(id);
        if (applicantOptional.isEmpty()) {
            throw new NullPointerException();
        }
        Applicant applicant = applicantOptional.get();
        applicant.setIsAccepted(false);
        addApplicant(applicant);
        return applicant;
    }

    public List<Long> deleteAllDeclined() {
        List<Applicant> allApplicantList = getAllApplicants();
        List<Long> declinedApplicantIds = allApplicantList.stream()
                .filter(a -> !a.getIsAccepted())
                .map(Applicant::getId)
                .toList();
        deleteApplicantsByIds(declinedApplicantIds);
        return declinedApplicantIds;
    }

}

