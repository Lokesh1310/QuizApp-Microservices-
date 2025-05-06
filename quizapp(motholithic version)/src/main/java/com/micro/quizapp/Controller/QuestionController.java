package com.micro.quizapp.Controller;

import com.micro.quizapp.model.Question;
import com.micro.quizapp.Service.QuestionSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    QuestionSevice questionSevice;

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
}
