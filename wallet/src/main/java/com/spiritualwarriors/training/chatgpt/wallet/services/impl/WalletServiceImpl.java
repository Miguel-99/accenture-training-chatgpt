package com.spiritualwarriors.training.chatgpt.wallet.services.impl;

import com.spiritualwarriors.training.chatgpt.wallet.entities.Wallet;
import com.spiritualwarriors.training.chatgpt.wallet.feign.UserClient;
import com.spiritualwarriors.training.chatgpt.wallet.feign.dtos.User;
import com.spiritualwarriors.training.chatgpt.wallet.services.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    private static final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);
    private static Double QUESTION_VALUE = 100.0;
    private final UserClient userClient;

    List<Wallet> wallets = List.of(
            new Wallet(1L, 500.0),
            new Wallet(2L, 100.0),
            new Wallet(3L, 20_000.0)
    );

    public WalletServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public Double getUserMoney(Long userId) {
        return wallets.stream().filter(w -> w.getUserId().equals(userId)).findFirst()
                .orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND))
                .getMoney();
    }

    @Override
    public void payQuestion(Long userId) {
        User user = userClient.getUser(userId);

        if (user.questionsAskedAmount() >= 3) {
            Wallet userWallet = wallets.stream().filter(w -> w.getUserId().equals(userId)).findFirst().get();
            userWallet.setMoney(userWallet.getMoney() - QUESTION_VALUE);
        }

        userClient.updateUser(user.questionsAskedAmount() + 1, userId);
    }

    @Override
    public void setQuestionValue(Double value) {
        QUESTION_VALUE = value;
        log.info("Question value set to {}", QUESTION_VALUE);
    }
}
