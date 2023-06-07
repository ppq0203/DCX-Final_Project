package project.momento.answer.controller;

import lombok.extern.log4j.Log4j2;
import project.momento.answer.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
public class AnswerController {
    
	@Autowired
	private AnswerService answerService;


}