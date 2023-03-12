package com.example.valorantrecruiting.kafka.config;

import com.example.valorantrecruiting.model.Applicant;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.apache.kafka.clients.consumer.StickyAssignor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsConfig {

    @Bean
    public ConsumerFactory<String, Applicant> applciantConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "127.0.0.1:9092");
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,"valorant_recruiting");

        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, StickyAssignor.class.getName());

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Applicant.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Applicant>
    applicantConcurrentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Applicant> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(applciantConsumerFactory());
        return factory;
    }
}
