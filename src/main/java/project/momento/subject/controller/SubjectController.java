package project.momento.subject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value="/subject.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String selectSubject( Model model, SubjectDto subjectDto) {
	   
		subjectService.selectSubject(subjectDto);
		
		return "redirect:/main.com";
	}
	
}
