package project.momento.qnaforms.controller;

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

import project.momento.question.dto.QuestionDto;
import project.momento.question.service.QuestionService;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class QnaformsController {
	
	@Autowired
	private SignService SignService;
	
	/*
	 * 학생 화면 페이지 이동
	 * param
	 * return contents/qnaforms 받는값
	 */
	@RequestMapping(value="/qnaforms.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String qnaformsMain( Model model) {
	    return "content/qnaforms";
	}
	
	//실행 결과 TEST//
	@RequestMapping(value = "/yttest", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String yttest() {
		////////////
		//데이터 저장
//		QuestionDto qtDto = new QuestionDto();
//		qtDto.setPkQuesTionSeq(0);
//		qtDto.setPkUserSeq(0);
//		qtDto.setAnswerUser("answeor");
//		qtDto.setAnswerOX("O");
//		QuestionService.insertQuestion(qtDto);
//		System.out.println();
		/////////////
		// 데이터 불러오기
		List<QuestionDto> question= QuestionService.selectQuestion(0);
		System.out.println(question);
		/////////////
		// 데이터 삭제
		return "index";
	}
}