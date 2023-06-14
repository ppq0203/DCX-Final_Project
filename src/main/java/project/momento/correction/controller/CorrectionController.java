package project.momento.correction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.correction.service.CorrectionService;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.question.dto.QuestionDto;

@Controller
public class CorrectionController {
	
	@Autowired
	private CorrectionService correctionService;

	/*
	 * 오답노트 main 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/correction/main", produces = "application/text;charset=utf-8")
	public String correctionMain(@PathVariable String userDivn, HttpServletRequest request, Criteria cri, Model model) {
		int total = 0;
		total = correctionService.selectQuestionListCount();
		// 페이징 객체
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		System.out.println(paging);
		System.out.println(cri);
		
		List<QuestionDto> resultList = correctionService.selectQuestionList(cri);
		
		model.addAttribute("paging", paging);
		model.addAttribute("resultList",resultList);
		return "content/" + userDivn + "/correction/main";
	}
	/*
	 * 오답노트 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/correction/note", produces = "application/text;charset=utf-8")
	public String aiQuiz(@PathVariable String userDivn, HttpServletRequest request, Model model) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		QuestionDto questionDto = new QuestionDto();
		questionDto.setPkUserSeq(loginDto.getPkUserSeq());
		List<QuestionDto> resultList = correctionService.selectQuestionResultList(questionDto);
		model.addAttribute("resultList",resultList);
		System.out.println(resultList);
		return "content/" + userDivn + "/correctionnote/correctionnote";
	}
	
	@RequestMapping(value = "/{userDivn}/correction/dict", produces = "application/text;charset=utf-8")
	public String aiDict(@PathVariable String userDivn, HttpServletRequest request, Model model) {

		return "content/" + userDivn + "/correctiondict/correctiondict";
	}
}
