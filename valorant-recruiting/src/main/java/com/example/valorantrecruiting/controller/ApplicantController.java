package com.example.valorantrecruiting.controller;

import com.example.valorantrecruiting.controller.command.ApplicantCommand;
import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//the only controller in this application
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class ApplicantController {

    private final ApplicantService applicantService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/view_all_applicants")
    public ResponseEntity<List<Applicant>> viewAllApplicants() {
        return new ResponseEntity<>(applicantService.getAllApplicants(), HttpStatusCode.valueOf(200));
    }


    @SneakyThrows
    @PostMapping("/accept_applicant")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Applicant acceptApplicant(@RequestBody @Valid ApplicantCommand applicantCommand) {
        return applicantService.acceptApplicant(applicantCommand.getId());
    }


    @SneakyThrows
    @PostMapping("/decline_applicant")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Applicant declineApplicant(@RequestBody @Valid ApplicantCommand applicantCommand) {
        return applicantService.declineApplicant(applicantCommand.getId());
    }

    @DeleteMapping("/delete_all_declined")
    public List<Long> deleteAllDeclined() {
        return applicantService.deleteAllDeclined();
    }
}
