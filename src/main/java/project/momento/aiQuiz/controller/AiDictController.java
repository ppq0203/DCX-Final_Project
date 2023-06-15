package project.momento.aiQuiz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.ai.service.AiService;
import project.momento.aiQuiz.dto.AiDictDto;
import project.momento.aiQuiz.service.AiDictService;
import project.momento.education.dto.EducationDto;
import project.momento.exam.dto.ExamDto;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.qna.dto.QnaDto;
import project.momento.question.dto.QuestionDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;
import project.momento.subject.dto.SubjectDto;

@Controller
public class AiDictController {

	@Autowired
	private AiDictService aidictService;
	
	
	@RequestMapping(value = "/{userDivn}/ai/dict", produces = "application/text;charset=utf-8")
	public String aiMain(@PathVariable String userDivn, HttpServletRequest request, Criteria cri, Model model) {
		int total = 0;
		total = aidictService.selectDictListCount();
		// 페이징 객체
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		System.out.println(paging);
		System.out.println(cri);
		
		List<AiDictDto> resultList = aidictService.selectDictList(cri);
		
		System.out.println(resultList);
		model.addAttribute("paging", paging);
		model.addAttribute("resultList",resultList);
		System.out.println("ok"); 
		return "content/" + userDivn + "/aidict/aidict";
	}


	/*
	 * AI Dictionary Insert
	 */
	
//	@RequestMapping(value = "/std/aidict/create", produces = "application/text;charset=utf-8") /* value주소 불러오기 이름 */
//	public String insertAiDict(AiDictDto aidictDto) {
//		aidictService.insertAiDict(aidictDto);
//		return "redirect:/std/ai/dict";
//	}

	
	/*
	 * AI Dictionary Delete
	 */
	@RequestMapping(value = "/{userDivn}/ai/dict/delete/{pkDictionarySeq}", produces = "application/text;charset=utf-8")
	public String aiQuestionSubmit(@PathVariable int pkDictionarySeq, @PathVariable String userDivn, HttpServletRequest request, Model model) {
		AiDictDto aidictDto = new AiDictDto();
		aidictDto.setPkDictionarySeq(pkDictionarySeq);
		aidictService.deleteAiDict(aidictDto);
		//aiService.selectQuestion(questionDto);
		
		return "redirect:/" + userDivn + "/ai/dict";
	}
	
	/*
	 * AI Dictionary Modify
	 */
	@RequestMapping(value = "/{userDivn}/aidict/{divn}", produces = "application/text;charset=utf-8")
	public String aiQuestionSubmit(int pkDictionarySeq, AiDictDto aidictDto, @PathVariable String userDivn, @PathVariable String divn, HttpServletRequest request, Model model) {
		System.out.println(aidictDto);
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		aidictDto.setUserId(loginDto.getUserId());
		if(divn.equals("create")) {
			aidictService.insertAiDict(aidictDto);
		}else if(divn.equals("update")){
			aidictService.updateAiDict(aidictDto);
			//aiService.selectQuestion(questionDto);
		}
		return "redirect:/" + userDivn + "/ai/dict";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDictList", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public AiDictDto getDictList(@RequestParam int pkDictionarySeq) {
		AiDictDto dictDto = aidictService.getDictList(pkDictionarySeq);
		System.out.println(dictDto);
		return dictDto;
	}
	
//	@RequestMapping(value = "/std/aidict/update", produces = "application/text;charset=utf-8") /* value주소 불러오기 이름 */
//	public String updateAiDict(@PathVariable int pkDictionarySeq, AiDictDto aidictDto, @PathVariable String userDivn, HttpServletRequest request, Model model) {
//		AiDictDto result = aidictService.getDictList(pkDictionarySeq);
//		
//		aidictService.updateAiDict(aidictDto);
//		return "redirect:/std/ai/dict";
//	}

}