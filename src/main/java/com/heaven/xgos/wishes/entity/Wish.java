package com.heaven.xgos.wishes.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Wish {
    long wishId;
    long kidId;
    long itemId;
    long shopId;
    int score;
    LocalDateTime wishDate;
    LocalDateTime orderDate;
    LocalDateTime receivedDate;
    LocalDateTime deliveredDate;
}
