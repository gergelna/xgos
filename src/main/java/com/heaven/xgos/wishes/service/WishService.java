package com.heaven.xgos.wishes.service;

import com.heaven.xgos.wishes.config.ApplicationProperties;
import com.heaven.xgos.wishes.dto.WishScore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WishService {

    private final ApplicationProperties properties;

    private final RestTemplate restTemplate;

    public WishService(ApplicationProperties applicationProperties,
                       RestTemplate restTemplate){
        this.properties = applicationProperties;
        this.restTemplate = restTemplate;
    }

    /**
     * Obtain the score of the wish from DeedsAndDonts
     * @param kidId
     * @param itemId
     * @return score
     */
    public int getWishScore(long kidId, long itemId) {
        String url = String.format("%s?kidId=%d&itemId=%d", properties.getDeedsAndDonts(), kidId, itemId);
        ResponseEntity<WishScore> response = restTemplate.getForEntity(url, WishScore.class);
        Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK), "ERROR - getScore failed");
        log.debug("getWishScore result:'{}'", response.getBody().getScore());

        return response.getBody().getScore();
    }
}
