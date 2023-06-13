package project.momento.login.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project.momento.file.dto.FileDto;
import project.momento.file.service.FileService;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private FileService fileService;

	/*
	 * 로그인 화면 이동 return content/userDivn/login/login
	 */
	@RequestMapping(value = "/{userDivn}/login/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginMngMain(@PathVariable String userDivn, Model model) {

		return "content/" + userDivn + "/login/login";
	}

	/*
	 * 로그인 체크 return contents/loginUp
	 */
	@RequestMapping(value = "/{userDivn}/login/form", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String loginForm(@PathVariable String userDivn, Model model, LoginDto loginDto, HttpServletRequest request) { // 입력값(id,pwd)를																														// 넣기
		
		loginDto.setUserDivn(userDivn);
		System.out.println(loginDto);
		LoginDto loginCheck = new LoginDto(); // loginDto 를 체크에넣기
		loginCheck = loginService.checkLogin(loginDto);
		if (loginCheck == null) { // loginCheck안에있는 id, pwd에 값이 없으면
			return "content/" + userDivn + "/login/login"; // 로그인화면
		} else {
			FileDto fileDto = fileService.selectFile(loginCheck.getPkFileSeq());
			if (fileDto != null) {
				Path imagePath = Paths.get(fileDto.getFilePath());
				if (Files.exists(imagePath)) {
					try {
						byte[] imageBytes = Files.readAllBytes(imagePath);
		                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		                loginCheck.setImgPath(base64Image);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			request.getSession().setAttribute("loginDto", loginCheck); // 아이디 세션에 저장
			
			return "redirect:/" + loginCheck.getUserDivn() + "/main";
			// return "redirect:/"+loginCheck.getUserDivn()+"/main";
		}
	}

	@RequestMapping(value = "/{userDivn}/login/out", produces = "application/text;charset=utf-8")
	public String logout(@PathVariable String userDivn, HttpSession session, HttpServletRequest request)
			throws Exception {
		session.invalidate();
		return "redirect:/" + userDivn + "/login/main";
	}

	@ResponseBody
	@RequestMapping(value = "/getUserList", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public Map<String, List<LoginDto>> getUserList(HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		Map<String, List<LoginDto>> resultList = new HashMap<>();

		List<LoginDto> managerList = loginService.selectManagerList();
		resultList.put("managerList", managerList);

		List<LoginDto> userList = loginService.selectUserList();
		resultList.put("userList", userList);

		return resultList;
	}

	@RequestMapping(value = "/login/info", produces = "application/text;charset=utf-8")
	public String selectUser(Model model, LoginDto loginDto, HttpServletRequest request) {

		loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		int pkUserSeq = loginDto.getPkUserSeq();
		// pkUserSeq 값이 존재하는 경우에만 로직 실행
		if (pkUserSeq > 0) {
			// loginService.selectUser() 메소드 호출 시 loginDto 전달해서 로직 후 dto로 리턴
			LoginDto dto = loginService.selectUser(loginDto);
			model.addAttribute(dto);
		}
		String userDivn = loginDto.getUserDivn();
		return "content/" + userDivn + "/sign/sign2";
	}

	@RequestMapping(value = "/login/update", produces = "application/text;charset=utf-8")
	public String updateUser(LoginDto loginDto, HttpServletRequest request) {

		// 세션에 있는 pkUserSeq 가져와서 loginDto에 담기
		LoginDto beforeDto = (LoginDto) request.getSession().getAttribute("loginDto");
		loginDto.setPkUserSeq(beforeDto.getPkUserSeq());
		// loginDto에 있는 pkUserSeq를 가져와서 조건이 맞다면 로직 실행
		int pkUserSeq = loginDto.getPkUserSeq();
		if (pkUserSeq > 0) {
			// loginService.updateUser() 메소드 호출 시 loginDto 전달
			loginService.updateUser(loginDto);
		}
		String userDivn = beforeDto.getUserDivn();
		return "content/" + userDivn + "/main/main";
	}

	@RequestMapping(value = "/login/delete", produces = "application/text;charset=utf-8")
	public String deleteUser(LoginDto loginDto, HttpServletRequest request) {
		loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		int pkUserSeq = loginDto.getPkUserSeq();
		loginDto.setPkUserSeq(pkUserSeq);
		if (pkUserSeq > 0) {
			// loginService.deleteUser() 메소드 호출 시 loginDto 전달
			loginService.deleteUser(loginDto);
		}
		String userDivn = loginDto.getUserDivn();
		return "content/"+userDivn+"/main/main";
	}

	@RequestMapping(value = "/userRole.com", produces = "application/text;charset=utf-8")
	public String userRole(Model model, LoginDto loginDto) {
		String useYn = loginDto.getUseYn();
		loginDto.setUseYn(useYn);
		LoginDto dto = loginService.userYn(loginDto);
		model.addAttribute("loginDto", dto);
		return "content/std/sign/sign2";
	}
}
