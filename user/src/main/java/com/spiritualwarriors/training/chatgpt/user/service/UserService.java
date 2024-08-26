package com.spiritualwarriors.training.chatgpt.user.service;

import com.spiritualwarriors.training.chatgpt.user.entities.User;

public interface UserService {
    User getUser(long userId);

    void updateQuestions(long userId, int req);
}
