package project.momento.subject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
	@RequestMapping(value="/mng/subject/main", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectMain(Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/mng/subject/subjectMain"); 
		int total = 0;
//		total = chartService.getProductListCount(cri);
		// 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(total); 
        System.out.println(paging);
        System.out.println(cri);
        mv.addObject("paging", paging);
		return mv;
	}
	
	@RequestMapping(value="/mng/subject/form", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectForm(Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/mng/subject/subjectForm"); 
		return mv;
	}
}
