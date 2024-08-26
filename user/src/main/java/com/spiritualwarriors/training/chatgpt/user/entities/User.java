package com.spiritualwarriors.training.chatgpt.user.entities;

public class User {
    private Long id;
    private String username;
    private Integer questionsAskedAmount;

    public User(Long id, String username, Integer questionsAskedAmount) {
        this.id = id;
        this.username = username;
        this.questionsAskedAmount = questionsAskedAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getQuestionsAskedAmount() {
        return questionsAskedAmount;
    }

    public void setQuestionsAskedAmount(Integer questionsAskedAmount) {
        this.questionsAskedAmount = questionsAskedAmount;
    }
}
