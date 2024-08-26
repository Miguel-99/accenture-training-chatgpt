package com.spiritualwarriors.training.chatgpt.wallet.services;

public interface WalletService {
    Double getUserMoney(Long userId);

    void payQuestion(Long userId);

    void setQuestionValue(Double value);
}
