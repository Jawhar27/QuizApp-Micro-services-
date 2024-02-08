package com.example.Quiz.app.models;

import lombok.Data;

@Data
public class QuizAnswer {
    private int questionId;
    private String response;

    public QuizAnswer(int questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }
}
