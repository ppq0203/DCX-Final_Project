package project.momento.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;

@Controller
public class testController {

	@Autowired
	private LoginService loginService;
	/*
	 * 로그인 화면 이동
	 * return content/userDivn/login/login
	 */
	@RequestMapping(value = "/test", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String testList(Model model) {
		//tmp
		List testList = new ArrayList();
		for(int i =0; i < 37; i++)
		{
			testList.add(Integer.toHexString(i));
		}
		System.out.println(testList);
		return "content/std/test/test";
	}
	
	
}
