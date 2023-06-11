package project.momento.answer.controller;

import lombok.extern.log4j.Log4j2;
import project.momento.answer.dto.AnswerDto;
import project.momento.answer.service.AnswerService;
import project.momento.login.dto.LoginDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
public class AnswerController {
    
	@Autowired
	private AnswerService answerService;

	//실행 결과 TEST//
	@RequestMapping(value = "/ybtest", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String ybtest() {
		AnswerDto asDto = new AnswerDto();
		////////////
		//데이터 저장
		
//		asDto.setPkQuestionSeq(7);
//		asDto.setPkUserSeq(2);
//		asDto.setAnswerUser("h");
//		asDto.setAnswerOx("O");
//		asDto.setType("3");
//		
//		answerService.insertAnswer(asDto);
//		System.out.println(asDto);
		
		/////////////
		// 데이터 불러오기
//		List<AnswerDto> answers = answerService.selectAnswer(2);
//		System.out.println(answers);
		
		////////////
		//TYPE,PkAnswerSeq 불러오기
//		asDto.setPkAnswerSeq(2);
//		asDto.setType("20");
//		AnswerDto answersType = answerService.selectAnswerType(asDto);
//		System.out.println(answersType);
		
		////////////
		//solveTime,PkAnswerSeq 불러오기
		asDto.setPkAnswerSeq(2);
		asDto.setSolveTime("2023-06-09 16:29:01");
		AnswerDto answersTime = answerService.selectAnswerTime(asDto);
		System.out.println(answersTime);

		////////////
		//solveTime,TYPE,PkAnswerSeq 불러오기
//		asDto.setPkAnswerSeq(2);
//		asDto.setType("2");
//		asDto.setSolveTime("2023-06-09 16:29:01");
//		
//		AnswerDto answersTY = answerService.selectAnswerTY(asDto);
//		System.out.println(answersTY);
		
		
		/////////////
		// 데이터 삭제 X
		
		return "index";
	}
}