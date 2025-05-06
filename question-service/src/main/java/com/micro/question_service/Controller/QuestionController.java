package com.micro.question_service.Controller;


import com.micro.question_service.Service.QuestionSevice;
import com.micro.question_service.model.Question;
import com.micro.question_service.model.QuestionWrapper;
import com.micro.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    QuestionSevice questionSevice;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
      return questionSevice.getAllQuestions();
    }


    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category ){
  return questionSevice.getAllQuestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody  Question question){
    return  questionSevice.addQuestion(question);
    }

    @GetMapping("generate")
        public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions){
        return  questionSevice.getQuestionsForQuiz(categoryName,numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
    return  questionSevice.getQuestionsFromId(questionIds);
    }


    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
    return  questionSevice.getScore(responses);
    }


}
