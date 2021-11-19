package com.ssafy.starry.service;

import com.ssafy.starry.controller.dto.testDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "exam", groupId = "foo", containerFactory = "testDtoListener")
    public void consume(testDto testdto) {
//        System.out.println("consumer 들어옴");
//        System.out.printf("Consumed key1 : %s%n key2 : %s%n", testdto.getKey1(), testdto.getKey2());
        log.debug("consumer 들어옴");
        log.debug("Consumed key1 : %s%n key2 : %s%n", testdto.getKey1(), testdto.getKey2());
    }

    @KafkaListener(topics = "input", groupId = "foo")
    public void consumeTwitInput(String input) {
//        System.out.println("consumeTwitInput 들어옴");
//        System.out.printf("Consumed str : %s%n", input);
        log.debug("consumeTwitInput 들어옴");
        log.debug("Consumed str : %s%n", input);
    }
}
