package com;

import java.time.LocalDateTime;

public class Customer {
    final long EXPIRY_HOURS = 24;
    Integer id;
    Integer score;
    LocalDateTime expiryDate = LocalDateTime.now().plusHours(EXPIRY_HOURS);

    public Customer(Integer id, Integer score) {
        this.id = id;
        this.score = score;

    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }
}
