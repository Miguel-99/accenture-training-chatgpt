package com.spiritualwarriors.training.chatgpt.wallet.feign.dtos;

public record User(
        Long id,
        String username,
        Integer questionsAskedAmount
) {
}
