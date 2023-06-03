package project.momento.subject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value="/{userDivn}/subject/main/{pkSubjectSeq}", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectLectMain(@PathVariable String userDivn, @PathVariable int pkSubjectSeq, Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/"+userDivn+"/subject/subject"); 
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
	
	@RequestMapping(value="/{userDivn}/subject/main", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectMain(@PathVariable String userDivn, Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/"+userDivn+"/subject/subjectMain"); 
		int total = 0;
//		total = chartService.getProductListCount(cri);
		// 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(total); 
        System.out.println(paging);
        System.out.println(cri);
        List<LoginDto> resultList = loginService.selectUserList();
        
        mv.addObject("resultList", resultList);
        mv.addObject("paging", paging);
		return mv;
	}
	
	
	
	@RequestMapping(value="/mng/subject/form", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectForm(Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/mng/subject/subjectMain"); 
		return mv;
	}
	
	
	
	@RequestMapping(value="/mng/subject/create", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String subjectCreate(Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/mng/subject/subjectMain");
		System.out.println(subjectDto);
		return "redirect:/mng/subject/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/getSubjectList", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public List<SubjectDto> GetMenu(Criteria cri, Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		List<SubjectDto> resultList = subjectService.selectSubjectList(cri);
		System.out.println(resultList);
		model.addAttribute("resultList", resultList);
		return resultList;			
	}
}
