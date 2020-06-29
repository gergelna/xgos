package com.heaven.xgos.wishes.listener;

import com.heaven.xgos.wishes.entity.Wish;
import com.heaven.xgos.wishes.service.WishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class KafkaListenerTest {

    @Mock
    WishService wishService;

    @InjectMocks
    KafkaListener kafkaListener;

    @Test
    void messageReceived() {
        //given
        int excpectedScore = 100;
        long kidId = 3;
        long itemId = 4;
        Wish wish = Wish.builder().kidId(kidId).itemId(itemId).build();

        //mock configuration
        doReturn(excpectedScore).when(wishService).getWishScore(kidId, itemId);

        //when
        kafkaListener.messageReceived(wish);

        //then
        assertTrue(wish.getScore() == excpectedScore, "Wrong score");
    }
}