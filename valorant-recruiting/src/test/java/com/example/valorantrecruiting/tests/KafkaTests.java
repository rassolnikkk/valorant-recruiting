package com.example.valorantrecruiting.tests;

import com.example.valorantrecruiting.ValorantRecruitingApplication;
import com.example.valorantrecruiting.config.ContainersEnvironment;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ActiveProfiles("test")
@SpringBootTest(classes = ValorantRecruitingApplication.class)
@EnableKafka
public class KafkaTests extends ContainersEnvironment {

    private Map<Long, String> kafkaFilledMap = new HashMap<>();

    private KafkaTemplate<Long, String> kafkaUnderTest;


    public KafkaTests(KafkaTemplate<Long, String> kafkaUnderTest) {
        this.kafkaFilledMap = new HashMap<Long, String>();
        this.kafkaUnderTest = kafkaUnderTest;
    }

    private void kafkaProducer(){
        kafkaUnderTest.send("test_topic",12l, "myvalue");

    }



    @KafkaListener(topics = "test_topic")
    public void kafkaListener(ConsumerRecord<Long, String> record){
        Long receivedKey = record.key();
        String receivedValue = record.value();
        kafkaFilledMap.put(receivedKey, receivedValue);
    }
    @Test
    @Disabled
    public void shouldBeTrue() throws InterruptedException {
        kafkaProducer();
        Optional<String> value = Optional.ofNullable(kafkaFilledMap.get(12l));
        assertThat(value).isPresent();
    }

}
