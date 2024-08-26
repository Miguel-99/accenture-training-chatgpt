package com.spiritualwarriors.training.chatgpt.user.service.impl;

import com.spiritualwarriors.training.chatgpt.user.entities.User;
import com.spiritualwarriors.training.chatgpt.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private Map<Long, User> users;

    public UserServiceImpl() {
        this.users = Map.of(
                1L, new User(1L, "username1", 0),
                2L, new User(2L, "username2", 1)
        );
    }

    @Override
    public User getUser(long userId) {
        return users.get(userId);
    }

    @Override
    public void updateQuestions(long userId, int newQuestionAmount) {
        User user = users.get(userId);
        user.setQuestionsAskedAmount(newQuestionAmount);
    }
}
