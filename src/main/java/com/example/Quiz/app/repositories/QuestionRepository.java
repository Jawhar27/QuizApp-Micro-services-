package com.example.Quiz.app.repositories;

import com.example.Quiz.app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String categoryName);

    @Query(value = "SELECT * FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :noOfQues", nativeQuery = true)
    List<Question> findRandomQuestionByCategory(String category, int noOfQues);
}
