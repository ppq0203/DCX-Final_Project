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
		qtDto.setPkQuestionSeq(0);
		qtDto.setQuestionName("0");
		qtDto.setLevel("0");
		qtDto.setScore(0);
		qtDto.setType("0");
		qtDto.setContents("0");
		qtDto.setCodeFormat("0");
		qtDto.setSolution("0");
		qtDto.setChance("0");
		qtDto.setClassName("0");
		qtDto.setInput("0");
		qtDto.setOutput("0");
		qtDto.setUseYn("Y");
		qtDto.setDelYn("N");
		qtDto.setRegistId("0");
		qtDto.setRegistDt("0");
		qtDto.setUpdateId("0");
		qtDto.setUpdateDt("0");
		QuestionService.insertQuestion(qtDto);
		System.out.println();
//		/////////////
//		// 데이터 불러오기
//		List<QuestionDto> question= QuestionService.selectQuestion(0);
//		System.out.println(question);
//		/////////////
//		// 데이터 삭제
		return "index";
	}
}