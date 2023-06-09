package project.momento.answer.controller;

import lombok.extern.log4j.Log4j2;
import project.momento.answer.dto.AnswerDto;
import project.momento.answer.service.AnswerService;

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
		////////////
		//데이터 저장
//		AnswerDto asDto = new AnswerDto();
//		asDto.setPkQuestionSeq(7);
//		asDto.setPkUserSeq(2);
//		asDto.setAnswerUser("hell");
//		asDto.setAnswerOx("O");
//		answerService.insertAnswer(asDto);
//		System.out.println(asDto);
		
		/////////////
		// 데이터 불러오기
		List<AnswerDto> answers = answerService.selectAnswer(1);
		System.out.println(answers);
		
		/////////////
		// 데이터 삭제 X
		
		return "index";
	}
}