package com.fedorov.tryCW5sem.Repositories;

import com.fedorov.tryCW5sem.Entity.QandA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QandARepository extends JpaRepository<QandA, Integer> {
    List<QandA> findAllBySubject(String subject);
}
