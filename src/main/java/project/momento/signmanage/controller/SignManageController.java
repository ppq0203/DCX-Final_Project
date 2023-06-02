package project.momento.signmanage.controller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.menu.dto.MenuDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class SignManageController {
	
	@Autowired
	private SignService SignService;
	
	/*
	 * 학생 회원 관리 이동
	 * param
	 * return contents/sign 받는값
	 */
	@RequestMapping(value="/signManage.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String SignManage(Model model, SignDto signDto, HttpServletRequest request) {
		List<SignDto> wList = SignService.waitList(signDto);
		//가입대기중인 계정을 모아 모델로 넘겨주기.
		model.addAttribute("wait", wList);
	    return "content/signManage";
	}
	
	@ResponseBody //ajax로 가입 대기중인 데이터 컨트롤.
	@RequestMapping(value="/waitUser.com", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public List<SignDto> waitUser(Model model, HttpServletRequest request, SignDto signDto) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		List<SignDto> waitList = SignService.waitList(signDto);
		model.addAttribute("waitList", waitList);
		return waitList;			
	}

	@ResponseBody //ajax로 가입 대기중인 데이터 컨트롤.
	@RequestMapping(value="/stdUser.com", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public List<SignDto> stdUser(Model model, HttpServletRequest request, SignDto signDto) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		List<SignDto> stdList = SignService.stdList(signDto);
		System.out.println(stdList);
		model.addAttribute("stdList", stdList);
		return stdList;			
	}
	
	@ResponseBody
	@RequestMapping(value="/signUser.com", produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public void permitsign(@RequestBody List<SignDto> permits) {
		for(SignDto permit : permits)
		{
			SignService.insertUser(permit);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/signAlluser.com", produces="application/json;charset=utf-8", method=RequestMethod.GET)
	public void permitAllsign() {
		SignService.signAlluser();
	}
}