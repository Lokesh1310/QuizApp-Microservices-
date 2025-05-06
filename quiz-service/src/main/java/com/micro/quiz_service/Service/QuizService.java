package com.micro.quiz_service.Service;

import com.micro.quiz_service.Repository.QuizRepository;
import com.micro.quiz_service.feign.QuizInterface;
import com.micro.quiz_service.model.QuestionWrapper;
import com.micro.quiz_service.model.Quiz;
import com.micro.quiz_service.model.Response;
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
    QuizInterface quizInterface;

    @Autowired
    QuizRepository quizRepository;


//    @Autowired
//    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions =quizInterface.getQuestionsForQuiz(category,numQ).getBody();

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz=  quizRepository.findById(id).get();
        List<Integer> questionIds=quiz.getQuestionIds();
       List<QuestionWrapper>  questions =quizInterface.getQuestionsFromId(questionIds).getBody();
       return  new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
       ResponseEntity<Integer> score= quizInterface.getScore(responses);
         return  score;
    }
}
