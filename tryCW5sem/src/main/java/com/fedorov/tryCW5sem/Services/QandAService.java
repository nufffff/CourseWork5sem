package com.fedorov.tryCW5sem.Services;

import com.fedorov.tryCW5sem.Entity.QandA;
import com.fedorov.tryCW5sem.Repositories.QandARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QandAService {
    private QandARepository qandARepository;

    @Autowired
    public QandAService(QandARepository qandARepository) {
        this.qandARepository = qandARepository;
    }

    public List<QandA> getQuestionsBySubject(String subject){
        return (List<QandA>) qandARepository.findAllBySubject(subject);
    }

    public void save(QandA qandA){
        qandARepository.save(qandA);
    }

    public List<QandA> findALl(){
        return qandARepository.findAll();
    }
}
