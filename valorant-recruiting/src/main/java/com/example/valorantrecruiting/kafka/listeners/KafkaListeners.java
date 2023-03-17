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
    @KafkaListener(topics = "applicant", groupId = "valorant_recruiting", containerFactory = "applicantConcurrentKafkaListenerContainerFactory")
    private void applicantTopicListener(Applicant applicant){
        applicantService.addApplicant(applicant);
    }
}
