package project.momento.login.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	/*
	 * 로그인 페이지 이동
	 * param
	 * return contents/login
	 */
	
	@RequestMapping(value="/login.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String gologinMain( Model model) {
	    return "content/login";
	}
	/*
	 * 로그인 페이지 ->회원가입 페이지 이동 
	 *           ->메인 페이지 이동
	 * param loginUp
	 * return contents/loginUp
	 */
	
	@RequestMapping(value="/loginUp.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String goSignMain( Model model, LoginDto loginDto, HttpServletRequest request) {  // 입력값(id,pwd)를 loginDto에 넣기

		LoginDto loginCheck = new LoginDto(); //loginDto 를 체크에넣기
		loginCheck = loginService.checkLogin(loginDto);
		if(loginCheck == null) { //loginCheck안에있는 id, pwd에 값이 없으면
			return "content/login";   //로그인화면
		}else {
			request.getSession().setAttribute("loginDto", loginCheck);  // 아이디 세션에 저장
				
			return "redirect:/main.com";
		}
	}
	
	@RequestMapping(value="/logout.com", produces="application/text;charset=utf-8") 
	public String logout(HttpSession session, HttpServletRequest request)throws Exception {
		session.invalidate();
		return "redirect:/login.com";
	}
	/**
	 * 로그아웃 한다.
	 * @return redirect:/loginMain
	 * @exception Exception
	 */
	@GetMapping("/logout")
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute("loginDto", null);
		return "redirect:/login.com";
	}
	
	public List<String>userList(HttpServletRequest request)
	{	
		return null;
	}
}


