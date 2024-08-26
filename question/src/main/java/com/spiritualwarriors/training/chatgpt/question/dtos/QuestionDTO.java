package com.spiritualwarriors.training.chatgpt.question.dtos;

import java.util.List;

public record QuestionDTO(
        List<String> keywords,
        String response
){}
