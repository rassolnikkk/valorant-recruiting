package com.example.valorantrecruiting.controller;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//the only controller in this application
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class ApplicantController {

    private final ApplicantService applicantService;


    @GetMapping("/view_all_applicants")
    public List<Applicant> viewAllApplicants() {
        return applicantService.getAllApplicants();
    }


    @PostMapping("/accept_applicant")
    public ResponseEntity<Applicant> acceptApplicant(@RequestBody Long id) {
        return applicantService.acceptApplicant(id);
    }


    @PostMapping("/decline_applicant")
    public ResponseEntity<Applicant> declineApplicant(@RequestBody Long id) {
        return applicantService.declineApplicant(id);
    }

    @DeleteMapping("/delete_all_declined")
    public ResponseEntity deleteAllDeclined() {
        return applicantService.deleteAllDeclined();
    }
}
