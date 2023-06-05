package project.momento.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	 * 관리자 로그인 화면 이동 return content/mng/login/login
	 */

	@RequestMapping(value = "/mng/login/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginMngMain(Model model) {

		return "content/mng/login/login";
		}
	/*
	 * 학생 로그인 화면 이동 return content/std/login/login
	 */

	@RequestMapping(value = "/std/login/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginStdMain(Model model) {

		return "content/std/login/login";
	}

	/*
	 * 로그인 체크 return contents/loginUp
	 */
	@RequestMapping(value = "/{userDivn}/login/form", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginForm(@PathVariable String userDivn, Model model, LoginDto loginDto, HttpServletRequest request) { // 입력값(id,pwd)를
																															// loginDto에
																															// 넣기
		LoginDto loginCheck = new LoginDto(); // loginDto 를 체크에넣기
		loginCheck = loginService.checkLogin(loginDto);
		if (loginCheck == null) { // loginCheck안에있는 id, pwd에 값이 없으면
			return "content/" + userDivn + "/login/login"; // 로그인화면
		} else {
			request.getSession().setAttribute("loginDto", loginCheck); // 아이디 세션에 저장
			return "redirect:/" + userDivn + "/main";
		}
	}

	@RequestMapping(value = "/{userDivn}/login/out", produces = "application/text;charset=utf-8")
	public String logout(@PathVariable String userDivn, HttpSession session, HttpServletRequest request)
			throws Exception {
		session.invalidate();
		return "redirect:/" + userDivn + "/login/main";
	}

	public List<String> userList(HttpServletRequest request) {
		return null;
	}

	@RequestMapping(value = "/select.com", produces = "application/text;charset=utf-8")
	public String selectUser(Model model, LoginDto loginDto, HttpServletRequest request) {
		loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		int pkUserSeq=loginDto.getPkUserSeq();
		// pkUserSeq 값이 존재하는 경우에만 로직 실행
		if (pkUserSeq > 0) {
			// loginService.selectUser() 메소드 호출 시 loginDto 전달해서 로직 후 dto로 리턴
			LoginDto dto = loginService.selectUser(loginDto);
			model.addAttribute(dto);
		}

		return "content/std/sign/sign2";
	}

	@RequestMapping(value = "/update.com", produces = "application/text;charset=utf-8")
	public String updateUser(Model model, LoginDto loginDto, HttpServletRequest request) {
		// 세션에 있는 pkUserSeq 가져와서 loginDto에 담기
		LoginDto beforeDto = (LoginDto) request.getSession().getAttribute("loginDto");
		loginDto.setPkUserSeq(beforeDto.getPkUserSeq());
		// loginDto에 있는 pkUserSeq를 가져와서 조건이 맞다면 로직 실행
		int pkUserSeq = loginDto.getPkUserSeq();
		if (pkUserSeq > 0) {
			// loginService.updateUser() 메소드 호출 시 loginDto 전달
			loginService.updateUser(loginDto);
		}
		return "content/std/sign/sign2";
	}

	@RequestMapping(value = "/userRole.com", produces = "application/text;charset=utf-8")
	public String userRole(Model model, LoginDto loginDto) {
		String useYn = loginDto.getUseYn();
		loginDto.setUseYn(useYn);
		loginService.userYn(loginDto);
		model.addAttribute("loginDto", loginDto);
		return "content/std/sign/sign2";
	}
}
