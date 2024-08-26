package com.spiritualwarriors.training.chatgpt.question.services;

import com.spiritualwarriors.training.chatgpt.question.dtos.AnswerRequest;
import com.spiritualwarriors.training.chatgpt.question.dtos.AuthInfo;
import com.spiritualwarriors.training.chatgpt.question.dtos.QuestionDTO;

import java.util.Optional;

public interface QuestionService {
    void addQuestion(QuestionDTO question);

    Optional<String> calculateAnswer(AnswerRequest question, AuthInfo authInfo);
}
