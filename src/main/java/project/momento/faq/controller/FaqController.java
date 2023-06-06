package project.momento.faq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaqController {
	
	/*
	 * 학생 화면 페이지 이동
	 * param
	 * return contents/studentScreen 받는값
	 */
	@RequestMapping(value="/faq.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String FAQ( Model model) {
	    return "content/faq";
	}
	
}