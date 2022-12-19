package com.fedorov.tryCW5sem.Services;

import com.fedorov.tryCW5sem.Entity.Answer;
import com.fedorov.tryCW5sem.Repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }

    public List<Answer> findAll(){
        return answerRepository.findAll();
    }
}
