package project.momento.subject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
	@RequestMapping(value="/subject.com", produces="application/text;charset=utf-8", method = RequestMethod.POST) /* value주소 이름*/
	public String selectSubject(){ 
		List<String> list = new ArrayList<String>();
		
		return "redirect:/main.com";
	}
	
}
