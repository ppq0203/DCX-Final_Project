package project.momento.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.menu.service.MenuService;
import project.momento.page.Criteria;

@Controller
public class QnaController {

	/*
	 * @Autowired private SignService SignService;
	 */

	@Autowired
	private MenuService menuService;
	@Autowired
	private LoginService loginService;

	/*
	 * Q&A 페이지 이동
	 * param
	 * return contents/qnaforms 받는값
	 */
	@RequestMapping(value="/{userDivn}/qna/main", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String qnasMain(@PathVariable String userDivn, Model model) {
	    
		
		return "content/"+userDivn+"/qna/qna";
	}

}
