package project.momento.main.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class MainController {

	@Autowired
	private SignService SignService;

	/*
	 * 
	 */
	@RequestMapping(value = "/{userDivn}/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String Main(@PathVariable String userDivn, Model model, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		if (!(loginDto == null)) {
			return "content/" + userDivn + "/main/main";
		} else {
			return "redirect:/" + userDivn + "/login/main";
		}
	}

	/*
	 * @RequestMapping(value="/studentMain.com",
	 * produces="application/text;charset=utf-8") value주소 불러오기 이름 public String
	 * studetMain( Model model) { return "content/studentMain"; }
	 */

}
