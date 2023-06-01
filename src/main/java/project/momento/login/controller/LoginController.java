package project.momento.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	/*
	 * 관리자 로그인 화면 이동
	 * return content/mng/login/login
	 */

	@RequestMapping(value = "/mng/login/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginMngMain(Model model) {
		
		return "content/mng/login/login";
	}
	/*
	 * 학생 로그인 화면 이동
	 * return content/std/login/login
	 */

	@RequestMapping(value = "/std/login/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginStdMain(Model model) {
		
		return "content/std/login/login";
	}
	
	/*
	 * 로그인 체크
	 * return contents/loginUp
	 */
	@RequestMapping(value = "/{userDivn}/login/form", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginForm(@PathVariable String userDivn, Model model, LoginDto loginDto, HttpServletRequest request) { // 입력값(id,pwd)를 loginDto에 넣기

		LoginDto loginCheck = new LoginDto(); // loginDto 를 체크에넣기
		loginCheck = loginService.checkLogin(loginDto);
		if (loginCheck == null) { // loginCheck안에있는 id, pwd에 값이 없으면
			return "content/"+userDivn+"/login/login"; // 로그인화면
		} else {
			request.getSession().setAttribute("loginDto", loginCheck); // 아이디 세션에 저장
			return "redirect:/"+userDivn+"/main";
		}
	}

	@RequestMapping(value = "/logout.com", produces = "application/text;charset=utf-8")
	public String logout(HttpSession session, HttpServletRequest request) throws Exception {
		session.invalidate();
		return "redirect:/login.com";
	}


	public List<String> userList(HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/select.com", produces = "application/text;charset=utf-8")
	public String selectUser(Model model, LoginDto loginDto) {
		int pkUserSeq = loginDto.getPkUserSeq();
		loginDto.setPkUserSeq(pkUserSeq);
		// pkUserSeq 값이 존재하는 경우에만 로직 실행
		if (pkUserSeq > 0) {
			// loginService.selectUser() 메소드 호출 시 loginDto 전달
			loginService.selectUser(loginDto);

			model.addAttribute("loginDto", loginDto);
		}

		return "content/signManage";
	}

	@RequestMapping(value = "/update.com", produces = "application/text;charset=utf-8")
	public String updateUser(Model model, LoginDto loginDto) {
		int pkUserSeq = loginDto.getPkUserSeq();
		loginDto.setPkUserSeq(pkUserSeq);
		// pkUserSeq 값이 존재하는 경우에만 로직 실행
		if (pkUserSeq > 0) {
			// loginService.updateUser() 메소드 호출 시 loginDto 전달
			loginService.updateUser(loginDto);

			model.addAttribute("loginDto", loginDto);
		}
		return "content/signManage";
	}

	@RequestMapping(value = "/deleteUser.com", produces = "application/text;charset=utf-8")
	public String deleteUser(Model model, LoginDto loginDto) {
		int pkUserSeq = loginDto.getPkAuthSeq();
		loginDto.setPkUserSeq(pkUserSeq);
		// pkUserSeq 값이 존재하는 경우에만 로직 실행
		if (pkUserSeq > 0) {
			// loginService.deleteUser() 메소드 호출 시 loginDto 전달
			loginService.deleteUser(loginDto);
			model.addAttribute("loginDto", loginDto);
		}
		return "content/signManage";
	}

	@RequestMapping(value = "/userRole.com", produces = "application/text;charset=utf-8")
	public String userRole(Model model, LoginDto loginDto) {
		String useYn = loginDto.getUseYn();
		loginDto.setUseYn(useYn);
		loginService.userYn(loginDto);
		model.addAttribute("loginDto", loginDto);
		return "content/signManage";
	}
}
