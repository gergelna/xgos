package com.heaven.xgos.wishes.service;

import com.heaven.xgos.wishes.dto.WishScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WishServiceTest {

    @Value("${application.wishes.url.deedsAndDonts}")
    String deedsAndDontsUrl;

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    WishService wishService;

    @Test
    void getWishScore() {
        // given
        int kidId = 3;
        int itemId = 4;
        int expectedScore = 100;
        WishScore wishScore = new WishScore();
        wishScore.setScore(expectedScore);
        ResponseEntity<WishScore> response = new ResponseEntity<>(wishScore, HttpStatus.OK);
        String url = String.format("%s?kidId=%d&itemId=%d", deedsAndDontsUrl, kidId, itemId);

        // mock configuration
        doReturn(response).when(restTemplate).getForEntity(url, WishScore.class);

        // when
        int resultScore = wishService.getWishScore(kidId, itemId);

        // then
        assertTrue(resultScore == expectedScore);
    }
}