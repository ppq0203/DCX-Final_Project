package project.momento.ai.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.ai.service.AiService;
import project.momento.exam.dto.ExamDto;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.question.dto.QuestionDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;
import project.momento.subject.dto.SubjectDto;

@Controller
public class AiController {

	@Autowired
	private AiService aiService;

	/*
	 * AI Main 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/ai/main", produces = "application/text;charset=utf-8")
	public String aiMain(@PathVariable String userDivn, HttpServletRequest request, Criteria cri, Model model) {
		int total = 0;
		total = aiService.selectQuestionListCount();
		// 페이징 객체
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		System.out.println(paging);
		System.out.println(cri);
		
		List<QuestionDto> resultList = aiService.selectQuestionList(cri);
		
		model.addAttribute("paging", paging);
		model.addAttribute("resultList",resultList);
		return "content/" + userDivn + "/ai/main";
	}
	/*
	 * AI Main 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/ai/question/form/{pkQuestionSeq}", produces = "application/text;charset=utf-8")
	public String aiQuestionForm(@PathVariable String userDivn, @PathVariable int pkQuestionSeq, HttpServletRequest request, Criteria cri, Model model) {
		
		
		QuestionDto questionDto = new QuestionDto();
		questionDto.setPkQuestionSeq(pkQuestionSeq);
		QuestionDto selectDto = aiService.selectQuestion(questionDto);
		
		
		model.addAttribute("result", selectDto);
		return "content/" + userDivn + "/ai/aiQuestionForm";
	}
	/*
	 * AI aiQuiz 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/ai/quiz", produces = "application/text;charset=utf-8")
	public String aiQuiz(@PathVariable String userDivn, HttpServletRequest request, Model model) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		QuestionDto questionDto = new QuestionDto();
		questionDto.setPkUserSeq(loginDto.getPkUserSeq());
		List<QuestionDto> resultList = aiService.selectQuestionResultList(questionDto);
		model.addAttribute("resultList",resultList);
		System.out.println(resultList);
		return "content/" + userDivn + "/aiquiz/aiquiz";
	}
	/*
	 * AI aiDict 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/ai/dict", produces = "application/text;charset=utf-8")
	public String aiDict(@PathVariable String userDivn, HttpServletRequest request, Model model) {

		return "content/" + userDivn + "/aidict/aidict";
	}

}