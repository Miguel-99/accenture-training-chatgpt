package com.spiritualwarriors.training.chatgpt.user.controllers;

import com.spiritualwarriors.training.chatgpt.user.dtos.UpdateUserRequest;
import com.spiritualwarriors.training.chatgpt.user.entities.User;
import com.spiritualwarriors.training.chatgpt.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@RequestBody int newQuestionAmount, @PathVariable long userId) {
        userService.updateQuestions(userId, newQuestionAmount);
    }

}
