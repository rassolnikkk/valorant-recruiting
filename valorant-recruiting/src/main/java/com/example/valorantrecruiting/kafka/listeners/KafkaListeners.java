package com.example.valorantrecruiting.kafka.listeners;

import com.example.valorantrecruiting.model.Applicant;
import com.example.valorantrecruiting.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KafkaListeners {

    private final ApplicantService applicantService;

    //methods below  track all data sent from discord eventlistener classes and add it to my database
    @KafkaListener(topics = "rank", containerFactory = "applicantConcurrentKafkaListenerContainerFactory")
    public void rankTopicListener(Applicant applicant) {
        applicantService.updateApplicantIfExists(applicant, "rank");
    }

    @KafkaListener(topics = "roles", groupId = "valorant_recruiting", containerFactory = "applicantConcurrentKafkaListenerContainerFactory")
    public void rolesTopicListener(Applicant applicant)  {
      applicantService.updateApplicantIfExists(applicant, "role");

    }

    @KafkaListener(topics = "trackerlink", containerFactory = "applicantConcurrentKafkaListenerContainerFactory")
    public void trackerlinkTopicListener(Applicant applicant)  {
        applicantService.updateApplicantIfExists(applicant, "age");
    }

    @KafkaListener(topics = "age", containerFactory = "applicantConcurrentKafkaListenerContainerFactory")
    public void ageTopicListener(Applicant applicant)  {
        applicantService.updateApplicantIfExists(applicant, "link");
    }
}
