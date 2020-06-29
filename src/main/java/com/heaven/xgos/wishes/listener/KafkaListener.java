package com.heaven.xgos.wishes.listener;

import com.heaven.xgos.wishes.entity.Wish;
import com.heaven.xgos.wishes.service.WishService;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {

    private final WishService wishService;

    public KafkaListener(WishService wishService){
        this.wishService = wishService;
    }

    public void messageReceived(Wish wish){
        wish.setScore(wishService.getWishScore(wish.getKidId(), wish.getItemId()));
        //TODO call WishOrdering subcomponent
    }
}
