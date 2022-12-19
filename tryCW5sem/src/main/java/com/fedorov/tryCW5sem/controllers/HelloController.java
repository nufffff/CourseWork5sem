package com.fedorov.tryCW5sem.controllers;

import com.fedorov.tryCW5sem.Entity.QandA;
import com.fedorov.tryCW5sem.Model.PreTest;
import com.fedorov.tryCW5sem.Services.AnswerService;
import com.fedorov.tryCW5sem.Services.PersonDetailsService;
import com.fedorov.tryCW5sem.Services.QandAService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;


@Controller
public class HelloController {
    private final QandAService qandAService;
    private final PersonDetailsService personDetailsService;
    private final AnswerService answerService;
    private PreTest preTest;

    @Autowired
    public HelloController(QandAService qandAService, PersonDetailsService personDetailsService, AnswerService answerService) {
        this.qandAService = qandAService;
        this.personDetailsService = personDetailsService;
        this.answerService = answerService;
    }

    @GetMapping("/results")
    public String showResults(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        model.addAttribute("list", personDetailsService.getPerson(name).getAnswers());
        model.addAttribute("name", name);
        return "results";
    }

    @GetMapping("/admin")
    public String showAllResults(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        model.addAttribute("list", answerService.findAll());
        model.addAttribute("name", name);
        return "admin";
    }


    @GetMapping("/preTest")
    public String showTest(@ModelAttribute("preTest") PreTest preTest, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name", name);
        return "preTest";
    }

    @PostMapping("/preTest")
    public String goToTest(@ModelAttribute PreTest preTest, Model model) {
        this.preTest = preTest;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("/addQ")
    public String addQ(@ModelAttribute("QandA") QandA qandA, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        model.addAttribute("list",qandAService.findALl());
        model.addAttribute("name", name);
        return "addQ";
    }

    @PostMapping("/addQ")
    public String saveQ(@ModelAttribute QandA qandA) {
        qandAService.save(qandA);
        return "redirect:/addQ";
    }

    @GetMapping("/getQ")
    @ResponseBody
    public String getQuestionsAndAnswers(){
        List<QandA> list = this.qandAService.getQuestionsBySubject(preTest.getSubject().getDisplayValue());
        Collections.shuffle(list);
        return new Gson().toJson(list.subList(0, preTest.getQuestions()));
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name", name);
        return "index";
    }
}
