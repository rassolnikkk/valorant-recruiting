package com.example.valorantrecruiting.kafka.producers;

import com.example.valorantrecruiting.model.Applicant;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApplicantKafkaProducer {

    private final KafkaTemplate<String, Applicant> template;

    public void produceUserIdAndRank(Long id, String rank){
        Applicant applicant = new Applicant();
        applicant.setRank(rank);
        applicant.setId(id);
        template.send("rank", "key", applicant);
    }

    public void produceUserIdAndRole(Long id, String role){
        Applicant applicant = new Applicant();
        applicant.setRole(role);
        applicant.setId(id);
        template.send("roles", "key", applicant);
    }

    public void produceUserIdAndAge(Long id, Integer age){
        Applicant applicant = new Applicant();
        applicant.setAge(age);
        applicant.setId(id);
        template.send("age", "key", applicant);
    }

    public void produceUserIdAndLink(Long id, String link){
        Applicant applicant = new Applicant();
        applicant.setValoTrackerReference(link);
        applicant.setId(id);
        template.send("trackerlink", "key", applicant);
    }

}
