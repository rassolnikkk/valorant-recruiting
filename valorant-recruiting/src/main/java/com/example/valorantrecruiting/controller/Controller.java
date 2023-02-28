package com.example.valorantrecruiting.controller;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class Controller {

    private ApplicantService applicantService;
    @GetMapping("/viewall")
    public List<Applicant> viewALl(){
        return applicantService.getALlApplicants();
    }

    @PostMapping("/accept")
    public void acceptApplicant(@RequestParam Long id){
        Optional applicantOptional = applicantService.findApplicantById(id);
        if (applicantOptional.isPresent()){
            Applicant applicant = (Applicant) applicantOptional.get();
            applicant.setIsAccepted(true);
            applicantService.addApplicant(applicant);
        }
    }
    @PostMapping("/decline")
    public void declineApplicant(@RequestParam Long id){
        Optional applicantOptional = applicantService.findApplicantById(id);
        if (applicantOptional.isPresent()){
            Applicant applicant = (Applicant) applicantOptional.get();
            applicant.setIsAccepted(false);
            applicantService.addApplicant(applicant);
        }
    }

}
