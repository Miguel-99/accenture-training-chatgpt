package com.spiritualwarriors.training.chatgpt.question.services.impl;

import com.spiritualwarriors.training.chatgpt.question.dtos.AnswerRequest;
import com.spiritualwarriors.training.chatgpt.question.dtos.AuthInfo;
import com.spiritualwarriors.training.chatgpt.question.dtos.QuestionDTO;
import com.spiritualwarriors.training.chatgpt.question.feign.WalletService;
import com.spiritualwarriors.training.chatgpt.question.services.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);
    private List<QuestionDTO> questions = List.of(
            new QuestionDTO(
                List.of("ganador", "copa", "america", "gana", "ganó", "torneo"),
                "Argentina"
            ),
            new QuestionDTO(
                List.of("ganador", "mundial", "qatar", "2022", "equipo", "ganó", "gana", "torneo"),
                "Argentina"
            ),
            new QuestionDTO(
                    List.of("segundo", "mundial", "qatar", "2022", "equipo", "torneo", "final"),
                    "Francia"
            )
    );

    private final WalletService walletService;

    public QuestionServiceImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public void addQuestion(QuestionDTO question) {
        questions.add(question);
    }

    @Override
    public Optional<String> calculateAnswer(AnswerRequest question, AuthInfo authInfo) {
        walletService.payQuestion(authInfo.userId());

        List<String> splittedQuestionWords = Arrays.stream(strip(question).split(" "))
                .map(String::toLowerCase)
                .toList();

        return questions.stream()
                .filter(q -> calculateMatchPercentage(q.keywords(), splittedQuestionWords) > 0.8)
                .max(Comparator.comparingDouble(q -> calculateMatchPercentage(q.keywords(), splittedQuestionWords)))
                .map(QuestionDTO::response);
    }

    private String strip(AnswerRequest question) {
        return question.question().replaceAll("[¿?¡!,.]", "");
    }

    private double calculateMatchPercentage(List<String> keywords, List<String> words) {
        long matches = keywords.stream()
                .filter(words::contains)
                .count();
        return (double) matches / keywords.size();
    }
}
