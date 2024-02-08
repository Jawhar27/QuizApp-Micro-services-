package com.example.Quiz.app.services;

import com.example.Quiz.app.entity.Question;
import com.example.Quiz.app.entity.Quiz;
import com.example.Quiz.app.models.QuestionWrapper;
import com.example.Quiz.app.models.QuizAnswer;
import com.example.Quiz.app.repositories.QuestionRepository;
import com.example.Quiz.app.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String title, int noOfQues, String category){
//        GETTING QUESTION FOR THE QUIZ
        try{
            List<Question> questions= questionRepository.findRandomQuestionByCategory(category,noOfQues);
            Quiz newQuiz=new Quiz();
            newQuiz.setTitle(title);
            newQuiz.setQuestions(questions);
            quizRepository.save(newQuiz);
            return new ResponseEntity<>("Created", HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int quizId) {
        try{
           Optional<Quiz> quiz= quizRepository.findById(quizId);
           List<Question> questions=quiz.get().getQuestions();
         List<QuestionWrapper> wrappedQuestions= new ArrayList<>();
         for(Question q:questions){
             QuestionWrapper wrappedQuestion=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
             wrappedQuestions.add(wrappedQuestion);
         }
         return new ResponseEntity<>(wrappedQuestions, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> calculateQuiz(int quizId, List<QuizAnswer> answers) {
        try{
            Quiz quiz= quizRepository.findById(quizId).get();
            List<Question> questions=quiz.getQuestions();
            int marks=0;
            int i=0;
            for (QuizAnswer answer : answers){
                if(answer.getResponse().equals(questions.get(i).getRightAnswer())){
                    marks++;
                }
                    i++;
            }
            return new ResponseEntity<>(marks, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }
}
