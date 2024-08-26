package com.spiritualwarriors.training.chatgpt.question.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spiritualwarriors.training.chatgpt.question.dtos.AnswerRequest;
import com.spiritualwarriors.training.chatgpt.question.dtos.AuthInfo;
import com.spiritualwarriors.training.chatgpt.question.dtos.QuestionDTO;
import com.spiritualwarriors.training.chatgpt.question.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final ObjectMapper objectMapper;

    public QuestionController(QuestionService questionService, ObjectMapper objectMapper) {
        this.questionService = questionService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public void addQuestion(@RequestBody QuestionDTO question) {
        questionService.addQuestion(question);
    }

    @PostMapping("/ask")
    public String getAnswer(@RequestHeader("Authorization") String authorization,
                            @RequestBody AnswerRequest question) throws JsonProcessingException {
        String decodedAuth = decode(authorization);
        AuthInfo authInfo = objectMapper.readValue(decodedAuth, AuthInfo.class);
        Optional<String> answer = questionService.calculateAnswer(question, authInfo);
        return answer.orElse("No se pudo encontrar una respuesta. Por favor intente con otra pregunta");

    }

    private static String decode(String authorization) {
        String auth = authorization.substring("Bearer ".length());
        return new String(Base64.getDecoder().decode(auth));
    }


}
