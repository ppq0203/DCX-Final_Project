package project.momento.signmanage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class SignManageController {
	
	@Autowired
	private SignService SignService;
	@Autowired
	private LoginService loginService;
	
	/*
	 * 학생 회원 관리 이동
	 * param
	 * return contents/sign 받는값
	 */
	@RequestMapping(value="/signManage.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String SignManage(Model model, SignDto signDto, HttpServletRequest request) {
		List<String> wList = SignService.waitList(signDto);
		//가입대기중인 계정을 모아 모델로 넘겨주기.
		model.addAttribute("wait", wList);
	    return "content/signManage";
	}
	
	@ResponseBody
	@RequestMapping(value="getTraineeList.com", produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public List<LoginDto> GetTraineeList (Model model, HttpServletRequest request) {
		List<LoginDto> TraineeList = loginService.getTrainList();
		return TraineeList;
	}
	
}