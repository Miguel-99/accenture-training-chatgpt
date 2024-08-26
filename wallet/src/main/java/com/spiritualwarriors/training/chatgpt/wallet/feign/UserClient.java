package com.spiritualwarriors.training.chatgpt.wallet.feign;

import com.spiritualwarriors.training.chatgpt.wallet.feign.dtos.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user")
public interface UserClient {

    @GetMapping("/users/{userId}")
    User getUser(@PathVariable long userId);

    @PutMapping("/users/{userId}")
    void updateUser(@RequestBody int newQuestionAmount, @PathVariable long userId);
}
