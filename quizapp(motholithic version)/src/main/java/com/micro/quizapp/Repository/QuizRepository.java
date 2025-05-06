package com.micro.quizapp.Repository;

import com.micro.quizapp.model.Question;
import com.micro.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {



}
