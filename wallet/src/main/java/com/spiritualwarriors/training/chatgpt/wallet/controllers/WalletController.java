package com.spiritualwarriors.training.chatgpt.wallet.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiritualwarriors.training.chatgpt.wallet.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController()
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    public WalletController(WalletService walletService, ObjectMapper objectMapper) {
        this.walletService = walletService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Double> getMoney(@PathVariable Long userId, @RequestHeader("Authorization") String authorization) throws JsonProcessingException {
        String decodedAuth = decode(authorization);
        AuthInfo authInfo = objectMapper.readValue(decodedAuth, AuthInfo.class);

        if (authInfo.user.equals("ADMIN")) {
            return ResponseEntity.ok(walletService.getUserMoney(userId));
        }

        return ResponseEntity.status(403).build();
    }

    private static String decode(String authorization) {
        String auth = authorization.substring("Bearer ".length());
        return new String(Base64.getDecoder().decode(auth));
    }

    @PostMapping("/{userId}")
    public void payQuestion(@PathVariable Long userId) {
        walletService.payQuestion(userId);
    }

    @PutMapping("/question")
    public void payQuestion(@RequestBody Double value) {
        walletService.setQuestionValue(value);
    }

    record AuthInfo(String user){}
}
