package project.momento.question.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.TestcaseGenerator;
import project.momento.question.service.QuestionService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionService QuestionService;
	
	
	//실행 결과 TEST//
	@RequestMapping(value = "/yytest", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String yytest() {
		////////////
		//데이터 저장
		QuestionDto qtDto = new QuestionDto();
		
		String[] level = {"1","2","3"};
		int probNum = 36;
		qtDto.setLevels(level);
		qtDto.setProbNum(probNum);
		
		List<QuestionDto> sQuestionDto= QuestionService.selectQuestion(qtDto);
		System.out.println(sQuestionDto);
		/////////////
		// 데이터 불러오기
//		List<QuestionDto> question= QuestionService.selectQuestion(0);
//		System.out.println(question);
		/////////////
		// 데이터 삭제
		return "index";
	}
}