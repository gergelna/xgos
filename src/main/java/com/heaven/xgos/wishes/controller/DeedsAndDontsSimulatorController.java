package com.heaven.xgos.wishes.controller;

import com.heaven.xgos.wishes.dto.WishScore;
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
public class DeedsAndDontsSimulatorController {

    @GetMapping(value = "getScore")
    public ResponseEntity<WishScore> getScore(@RequestParam Long kidId, @RequestParam Long itemId){
        log.debug("getScore is received");
        WishScore wishScore = new WishScore();
        wishScore.setScore(100);
        return new ResponseEntity<>(wishScore, HttpStatus.OK);
    }
}
