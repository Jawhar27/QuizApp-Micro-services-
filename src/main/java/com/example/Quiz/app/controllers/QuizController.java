package com.example.Quiz.app.controllers;

import com.example.Quiz.app.entity.Question;
import com.example.Quiz.app.entity.Quiz;
import com.example.Quiz.app.models.QuestionWrapper;
import com.example.Quiz.app.models.QuizAnswer;
import com.example.Quiz.app.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("create")
    public ResponseEntity<String> create(@RequestParam String title, @RequestParam int noOfQuestions, @RequestParam String category){
        return quizService.createQuiz(title,noOfQuestions,category);
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> create(@PathVariable int quizId){
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("submit/{quizId}")
    public ResponseEntity<Integer> create(@PathVariable int quizId, @RequestBody List<QuizAnswer> quizAnswers){
        return quizService.calculateQuiz(quizId,quizAnswers);
    }
}
