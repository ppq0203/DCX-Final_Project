package project.momento.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.qna.dto.QnaDto;
import project.momento.qna.service.QnaService;

@Controller
public class QnaController {

	/*
	 * @Autowired private SignService SignService;
	 */

	@Autowired
	private QnaService qnaService;

	/*
	 * Q&A 페이지 이동
	 * param
	 * return contents/userDivn/qna/qna 받는값
	 */
	@RequestMapping(value="/{userDivn}/{pkEducationSeq}/{pkSubjectSeq}/qna/main", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String qnasMain(@PathVariable String userDivn, @PathVariable int pkEducationSeq, @PathVariable int pkSubjectSeq, Model model) {
		System.out.println("In qnasMain");
		return "content/"+userDivn+"/qna/qna";
	}
	
	@RequestMapping(value="/std/qna/create", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String insertQna(@PathVariable String userDivn, Model model, QnaDto qnaDto) {
		System.out.println("In insertQna");
		qnaService.insertQna(qnaDto);
		return "content/std/qna/qna";
	}

}
