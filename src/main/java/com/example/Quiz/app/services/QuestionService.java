package com.example.Quiz.app.services;

import com.example.Quiz.app.entity.Question;
import com.example.Quiz.app.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getQuestions(){
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>>  getQuestionsByCategory(String categoryName){
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(categoryName), HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> createQuestion(Question question){
        try {
            questionRepository.save(question);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
    }

    public String deleteQuestion(int questionId){
        questionRepository.deleteById(questionId);
        return "Success";
    }

}
