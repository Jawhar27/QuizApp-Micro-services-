package com.example.Quiz.app.repositories;

import com.example.Quiz.app.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
