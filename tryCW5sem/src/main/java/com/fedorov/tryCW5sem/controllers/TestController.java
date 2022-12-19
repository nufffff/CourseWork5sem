package com.fedorov.tryCW5sem.controllers;

import com.fedorov.tryCW5sem.Entity.Answer;
import com.fedorov.tryCW5sem.Services.AnswerService;
import com.fedorov.tryCW5sem.Services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private final PersonDetailsService personDetailsService;
    private final AnswerService answerService;

    @Autowired
    public TestController(PersonDetailsService personDetailsService, AnswerService answerService) {
        this.personDetailsService = personDetailsService;
        this.answerService = answerService;
    }

    @PostMapping("/test1")
    public void takeAnswer(@RequestBody Answer answer){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        answer.setPerson(personDetailsService.getPerson(name));
        answerService.saveAnswer(answer);
    }


}

