package com.spiritualwarriors.training.chatgpt.question.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "wallet")
public interface WalletService {

    @PostMapping("/wallets/{userId}")
    void payQuestion(@PathVariable Long userId);
}
