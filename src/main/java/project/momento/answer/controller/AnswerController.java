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
//		asDto.setAnswerUser("public class Solution {\r\n"
//				+ "\r\n"
//				+ "    public boolean isAnagram(String s, String t) {\r\n"
//				+ "        if (s.length() != t.length()) return false;\r\n"
//				+ "\r\n"
//				+ "        int[] store = new int[26];\r\n"
//				+ "\r\n"
//				+ "        for (int i = 0; i < s.length(); i++) {\r\n"
//				+ "            store[s.charAt(i) - 'a']++;\r\n"
//				+ "            store[t.charAt(i) - 'a']--;\r\n"
//				+ "        }\r\n"
//				+ "\r\n"
//				+ "        for (int n : store) if (n != 0) return false;\r\n"
//				+ "\r\n"
//				+ "        return true;\r\n"
//				+ "    }\r\n"
//				+ "}");
//		asDto.setAnswerOx("X");
//		asDto.setType("Hash Table");
//		
//		answerService.insertAnswer(asDto);
//		System.out.println(asDto);
		
		/////////////
		// 데이터 불러오기
//		List<AnswerDto> answers = answerService.selectAnswer(1);
//		System.out.println(answers);
		
		////////////
		//TYPE,PkUserSeq 불러오기
//		asDto.setPkUserSeq(1);
//		asDto.setType("Hash Table");
//		AnswerDto answersType = answerService.selectAnswerType(asDto);
//		System.out.println(answersType);
		
		////////////
		//solveTime,PkUserSeq 불러오기
//		asDto.setPkUserSeq(2);
//		asDto.setSolveTime("2023-06-11 13:49:10");
//		AnswerDto answersTime = answerService.selectAnswerTime(asDto);
//		System.out.println(answersTime);

		////////////
		//solveTime,TYPE,PkAnswerSeq 불러오기
		asDto.setPkUserSeq(2);
		asDto.setType("Hash Table");
		asDto.setSolveTime("2023-06-11 13:49:10");
		AnswerDto answersTY = answerService.selectAnswerTY(asDto);
		System.out.println(answersTY);
		
		
		/////////////
		// 데이터 삭제 X
		
		return "index";
	}
}