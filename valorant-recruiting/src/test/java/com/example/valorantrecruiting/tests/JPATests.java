package com.example.valorantrecruiting.tests;

import com.example.valorantrecruiting.ValorantRecruitingApplication;
import com.example.valorantrecruiting.config.ContainersEnvironment;
import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ActiveProfiles("test")
@SpringBootTest(classes = ValorantRecruitingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JPATests extends ContainersEnvironment{

    @Autowired
    private ApplicantService jpaUnderTest;


    @Test
    public void getAllApplicantsShouldReturnEmptyList(){
        List<Applicant> list = jpaUnderTest.getAllApplicants();
        assertEquals(0,list.size());
    }

    @Test
    public void checkIfAddedApplicantExistsShouldBeTrue(){
        Applicant applicant = new Applicant(1L);
        jpaUnderTest.addApplicant(applicant);
        boolean expected = jpaUnderTest.applicantExistsById(1L);
        assertThat(expected).isTrue();
        jpaUnderTest.deleteApplicant(applicant);
    }

    @Test
    public void findApplicantByIdShouldBeFalse(){
        Optional<Applicant> applicant = jpaUnderTest.findApplicantById(1l);
        assertThat(applicant).isNotPresent();
    }

    @Test
    public void deleteApplicantsByIdsShouldBeTrue(){
        Applicant applicant = new Applicant(2L);
        Applicant applicant1 = new Applicant(3L);
        Applicant applicant2 = new Applicant(5L);
        jpaUnderTest.addApplicant(applicant);
        jpaUnderTest.addApplicant(applicant1);
        jpaUnderTest.addApplicant(applicant2);
        assertThat(jpaUnderTest.getAllApplicants().size()).isEqualTo(3);
        List<Long> listOfIds = new ArrayList<>();
        listOfIds.add(2L);
        listOfIds.add(3L);
        jpaUnderTest.deleteApplicantsByIds(listOfIds);
        assertThat(jpaUnderTest.getAllApplicants().size()).isEqualTo(1);
        assertThat(jpaUnderTest.findApplicantById(5L)).isPresent();
    }


}
