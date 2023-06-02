package project.momento.subject.controller;

<<<<<<< HEAD
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
=======
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class SubjectController {
	
	@Autowired
	private SignService SignService;
	
	/*
	 * 학생 화면 페이지 이동
	 * param
	 * return contents/subject 받는값
	 */
	@RequestMapping(value="/subject.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String subjectMain( Model model) {
	    return "content/subject";
	}
	
}
>>>>>>> refs/remotes/origin/Game_Front_kjh
