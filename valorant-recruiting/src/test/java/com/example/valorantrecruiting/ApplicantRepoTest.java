package com.example.valorantrecruiting;

import com.example.valorantrecruiting.config.ContainersEnvironment;
import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ValorantRecruitingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicantRepoTest extends ContainersEnvironment {

    @Autowired
    private ApplicantService underTest;

    @Test
    public void getAllApplicantsShouldReturnEmptyList(){
        List<Applicant> list = underTest.getALlApplicants();
        assertEquals(0,list.size());
    }

    @Test
    public void checkIfAddedApplicantExistsShouldBeTrue(){
        Applicant applicant = new Applicant(1l);
        underTest.addApplicant(applicant);
        boolean expected = underTest.applicantExistsById(1l);
        assertThat(expected).isTrue();
        underTest.deleteApplicant(applicant);
    }

    @Test
    public void findApplicantByIdShouldBeFalse(){
        Optional<Applicant> applicant = underTest.findApplicantById(1l);
        assertThat(applicant).isNotPresent();
    }

    @Test
    public void deleteApplicantsByIdsShouldBeTrue(){
        Applicant applicant = new Applicant(2l);
        Applicant applicant1 = new Applicant(3l);
        Applicant applicant2 = new Applicant(5l);
        underTest.addApplicant(applicant);
        underTest.addApplicant(applicant1);
        underTest.addApplicant(applicant2);
        assertThat(underTest.getALlApplicants().size()).isEqualTo(3);
        List<Long> listOfIds = new ArrayList<>();
        listOfIds.add(2l);
        listOfIds.add(3l);
        underTest.deleteApplicantsByIds(listOfIds);
        assertThat(underTest.getALlApplicants().size()).isEqualTo(1);
        assertThat(underTest.findApplicantById(5l)).isPresent();
    }
}
