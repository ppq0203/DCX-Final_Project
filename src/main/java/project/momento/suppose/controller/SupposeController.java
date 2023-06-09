package project.momento.suppose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.sign.service.SignService;

@Controller
public class SupposeController {

	/*
	 * @Autowired private SignService SignService;
	 */

	@Autowired
	private SignService SignService;

	/*
	 * Q&A 페이지 이동
	 * param
	 * return contents/userDivn/qna/qna 받는값
	 */
	@RequestMapping(value="/{userDivn}/suppose/main", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String supposeMain(@PathVariable String userDivn, Model model, HttpServletRequest request) {
		request.getSession().setAttribute("tab", 3);
		return "content/"+userDivn+"/suppose/suppose";
	}

}
