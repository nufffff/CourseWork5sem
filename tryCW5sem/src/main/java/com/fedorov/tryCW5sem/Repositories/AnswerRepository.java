package com.fedorov.tryCW5sem.Repositories;

import com.fedorov.tryCW5sem.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
