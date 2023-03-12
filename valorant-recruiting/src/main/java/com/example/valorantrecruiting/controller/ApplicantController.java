package com.example.valorantrecruiting.controller;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//the only controller in this application
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class ApplicantController {

    private final ApplicantService applicantService;


    @GetMapping("/view_all_applicants")
    public ResponseEntity<List<Applicant>> viewAllApplicants() {
        return new ResponseEntity<>(applicantService.getAllApplicants(), HttpStatusCode.valueOf(200));
    }


    @SneakyThrows
    @PostMapping("/accept_applicant")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Applicant acceptApplicant(@RequestBody @Min(1l) Long id) {
        return applicantService.acceptApplicant(id);
    }


    @SneakyThrows
    @PostMapping("/decline_applicant")
    public Applicant declineApplicant(@RequestBody Long id) {
        if (id == null){
            throw new RuntimeException("the given id must not be null");
        }
        if (id <= 0){
            throw new RuntimeException("the given id must be above 0");
        }
        return applicantService.declineApplicant(id);
    }

    @DeleteMapping("/delete_all_declined")
    public List<Long> deleteAllDeclined() {
        return applicantService.deleteAllDeclined();
    }
}
