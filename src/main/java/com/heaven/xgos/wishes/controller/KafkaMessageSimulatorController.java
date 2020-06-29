package com.heaven.xgos.wishes.controller;

import com.heaven.xgos.wishes.dto.WishScore;
import com.heaven.xgos.wishes.entity.Wish;
import com.heaven.xgos.wishes.listener.KafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class KafkaMessageSimulatorController {

    private final KafkaListener kafkaListener;

    public KafkaMessageSimulatorController(KafkaListener kafkaListener){
        this.kafkaListener = kafkaListener;
    }

    /**
     * Simulates as if Kafka message would have been received until the Kafka implementation is ready.
     * @return 200 OK
     */
    @GetMapping(value = "kafka")
    public ResponseEntity<Wish> kafka(@RequestParam Long kidId, @RequestParam Long itemId){
        log.debug("Wish is received");

        Wish wish = Wish.builder().kidId(kidId).itemId(itemId).build();
        kafkaListener.messageReceived(wish);

        return new ResponseEntity<>(wish, HttpStatus.OK);
    }
}
