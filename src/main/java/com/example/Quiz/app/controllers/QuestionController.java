package com.example.Quiz.app.controllers;

import com.example.Quiz.app.entity.Question;
import com.example.Quiz.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getQuestions();
    }

    @GetMapping("category/{categoryName}")
    public ResponseEntity<List<Question>>  getAllQuestions(@PathVariable String categoryName){
        return questionService.getQuestionsByCategory(categoryName);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @DeleteMapping("delete/{questionId}")
    public String createQuestion(@PathVariable int questionId){
        return questionService.deleteQuestion(questionId);
    }
}
