package com.example.Quiz.app.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String questionTitle;
    private String category;
    private String difficultyLevel;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
}
