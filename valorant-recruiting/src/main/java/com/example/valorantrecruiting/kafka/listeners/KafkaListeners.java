package com.example.valorantrecruiting.kafka.listeners;

import com.example.valorantrecruiting.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
public class KafkaListeners {

    private final ApplicantService applicantService;

    //methods below  track all data sent from discord eventlistener classes and add it to my database
    @KafkaListener(topics = "rank",groupId = "valorant_recruiting")
    public void rankTopicListener(ConsumerRecord<Long, String> record) {
        applicantService.addApplicantWithIdAndRank(record.key(), record.value());
    }

    @KafkaListener(topics = "roles")
    public void rolesTopicListener(ConsumerRecord<Long, String> record)  {
      applicantService.addApplicantWithIdAndRole(record.key(), record.value());

    }

    @KafkaListener(topics = "trackerlink")
    public void trackerlinkTopicListener(ConsumerRecord<Long, String> record)  {
        applicantService.addApplicantWithIdAndTrackerLink(record.key(), record.value());
    }

    @KafkaListener(topics = "age")
    public void ageTopicListener(ConsumerRecord<Long, String> record)  {
        applicantService.addApplicantWithIdAndAge(record.key(), record.value());
    }
}
