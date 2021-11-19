package com.ssafy.starry.service;

import com.ssafy.starry.controller.dto.testDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    private static final String TOPIC = "exam";

    private final KafkaTemplate<String, testDto> KafkaTemplate;

    @Autowired
    public KafkaProducer(
        @Qualifier("jsonTemplate") KafkaTemplate<String, testDto> kafkaTemplate) {
        this.KafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(testDto testdto) {
//        System.out.printf("Produce key1 : %s%n key2 : %s%n", testdto.getKey1(), testdto.getKey2());
        log.debug("Produce key1 : %s%n key2 : %s%n", testdto.getKey1(), testdto.getKey2());
        this.KafkaTemplate.send(TOPIC, testdto.getKey1(), testdto);
    }


}