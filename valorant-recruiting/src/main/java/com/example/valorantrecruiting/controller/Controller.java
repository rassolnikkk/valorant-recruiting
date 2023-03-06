package com.example.valorantrecruiting.controller;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//the only controller in this application
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class Controller {

    private final ApplicantService applicantService;

    //method for getting all existing applicants
    @GetMapping("/viewall")
    public List<Applicant> viewALl(){
        return applicantService.getALlApplicants();
    }

    //method for accepting an applicant by passing his unique discord id in parameters
    @PostMapping("/accept")
    public void acceptApplicant(@RequestParam Long id){
        Optional applicantOptional = applicantService.findApplicantById(id);
        if (applicantOptional.isPresent()){
            Applicant applicant = (Applicant) applicantOptional.get();
            applicant.setIsAccepted(true);
            applicantService.addApplicant(applicant);
        }
    }
    //method for declining an applicant by passing his unique discord id in parameters
    @PostMapping("/decline")
    public void declineApplicant(@RequestParam Long id){
        Optional applicantOptional = applicantService.findApplicantById(id);
        if (applicantOptional.isPresent()){
            Applicant applicant = (Applicant) applicantOptional.get();
            applicant.setIsAccepted(false);
            applicantService.addApplicant(applicant);
        }
    }
    @DeleteMapping("/delete")
    public void deleteAllDeclined(){
        List<Applicant> list = applicantService.getALlApplicants();
        ArrayList<Long> declinedApplicantIds = new ArrayList<>();
        for (Applicant applicant:list) {
            if (applicant.getIsAccepted() != null){
                if (applicant.getIsAccepted() == false){
                   declinedApplicantIds.add(applicant.getId());
                }
            }
        }
        applicantService.deleteApplicantsByIds(declinedApplicantIds);

    }
}
