package com.micro.question_service.Service;

import com.micro.question_service.Repository.QuestionRepository;

import com.micro.question_service.model.Question;
import com.micro.question_service.model.QuestionWrapper;
import com.micro.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionSevice {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        }

    public ResponseEntity<List<Question>> getAllQuestionByCategory(String category) {

        try {


            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        }

    }


    public ResponseEntity<String> addQuestion(Question question) {
         questionRepository.save(question);
        try {
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions=questionRepository.findRandomQuestionsByCategory(categoryName,numQuestions);


        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
   List<QuestionWrapper> wrappers =new ArrayList<>();

List<Question> questions=new ArrayList<>();

for(Integer id:questionIds){
    questions.add(questionRepository.findById(id).get());
}

for(Question question:questions){

    QuestionWrapper wrapper=new QuestionWrapper();
    wrapper.setId(question.getId());
    wrapper.setQuestionTitle(question.getQuestionTitle());
    wrapper.setOption1(question.getOption1());
    wrapper.setOption2(question.getOption2());
    wrapper.setOption3(question.getOption3());
    wrapper.setOption4(question.getOption4());

    wrappers.add(wrapper);


}

return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }


    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int right=0;

        for(Response response:responses){
            Question question=questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return  new ResponseEntity<>(right,HttpStatus.OK);
    }
}
