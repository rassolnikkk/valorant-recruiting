package com.example.valorantrecruiting.service;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.model.Rank;
import com.example.valorantrecruiting.model.Role;
import com.example.valorantrecruiting.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepo;

    public List<Applicant> getAllApplicants() {
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

    public void deleteApplicantsByIds(List<Long> idsToDelete) {
        applicantRepo.deleteAllById(idsToDelete);
    }

    public void deleteApplicant(Applicant applicant) {
        applicantRepo.delete(applicant);
    }

    public ResponseEntity<Applicant> acceptApplicant(Long id) {
        Optional<Applicant> applicantOptional = findApplicantById(id);
        if (applicantOptional.isEmpty()) {
            return new ResponseEntity("User with id" + id + "doesnt exist", HttpStatus.NOT_FOUND);
        }
        Applicant applicant = applicantOptional.get();
        applicant.setIsAccepted(true);
        addApplicant(applicant);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    public ResponseEntity<Applicant> declineApplicant(Long id) {
        Optional<Applicant> applicantOptional = findApplicantById(id);
        if (applicantOptional.isEmpty()) {
            return new ResponseEntity("User with id" + id + "doesnt exist", HttpStatus.NOT_FOUND);
        }
        Applicant applicant = applicantOptional.get();
        applicant.setIsAccepted(false);
        addApplicant(applicant);
        return new ResponseEntity<>(applicant, HttpStatus.OK);
    }

    public ResponseEntity deleteAllDeclined() {
        List<Applicant> allApplicantList = getAllApplicants();
        List<Long> declinedApplicantIds = allApplicantList.stream()
                .filter(a -> !a.getIsAccepted())
                .map(Applicant::getId)
                .toList();
        deleteApplicantsByIds(declinedApplicantIds);
        return new ResponseEntity(HttpStatus.OK);
    }

    public void addApplicantWithIdAndRole(Long id, String role) {
        try {
            Role convertedToEnumRole = Role.valueOf(role);
            Applicant applicant = new Applicant();
            applicant.builder().id(id).role(convertedToEnumRole).build();
            applicantRepo.save(applicant);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void addApplicantWithIdAndRank(Long id, String rank) {
            Rank convertedToEnumRank = Rank.valueOf(rank);
            Applicant applicant = new Applicant();
            //applicant.builder().id(id).rank(convertedToEnumRank).build();
        applicant.setId(id);
        applicant.setRank(convertedToEnumRank);
            applicantRepo.save(applicant);

    }

    public void addApplicantWithIdAndAge(Long id, String age) {
        try {
            Integer convertedToIntAge = Integer.valueOf(age);
            Applicant applicant = new Applicant();
            applicant.builder().id(id).age(convertedToIntAge).build();
            applicantRepo.save(applicant);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void addApplicantWithIdAndTrackerLink(Long id, String valoTrackerLink){
        Applicant applicant = new Applicant();
        applicant.builder().id(id).valoTrackerReference(valoTrackerLink).build();
        applicantRepo.save(applicant);
    }

}

