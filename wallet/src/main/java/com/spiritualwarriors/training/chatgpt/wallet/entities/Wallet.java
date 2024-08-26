package com.spiritualwarriors.training.chatgpt.wallet.entities;

import java.util.UUID;

public class Wallet {
    private String id;
    private Long userId;
    private Double money;

    public Wallet(Long userId, Double money) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
