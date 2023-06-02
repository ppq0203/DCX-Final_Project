package project.momento.qna.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class QnaController {

	/*
	 * @Autowired private SignService SignService;
	 */

	@Autowired
	private SignService SignService;

	/*
	 * 사이트에서 이동
	 *
	 */
	/*
	 * @RequestMapping(value="/mypage.com",
	 * produces="application/text;charset=utf-8") value주소 이름 public String
	 * Mypage(Criteria cri, Model model, HttpServletRequest request) { // 세션에서 내 정보를
	 * 가져온다 LoginDto loginDto =
	 * (LoginDto)request.getSession().getAttribute("loginDto"); // 로그인이 되어있는지 확인
	 * if(!(loginDto == null)) { // 로그인이 되어있는 경우 List<MenuDto> menuList =
	 * menuService.getMenuList(loginDto.getPkAuthSeq());
	 * System.out.println(menuList); model.addAttribute("menuList", menuList);
	 * return "content/mypage"; } else { // 로그인이 되어있지 않은 경우 return "/login.com"; } }
	 * 
	 * @RequestMapping(value="/menumanagement.com",
	 * produces="application/text;charset=utf-8") public String MenuManagement(Model
	 * model, HttpServletRequest request) { // 세션에서 내 정보를 가져온다 LoginDto loginDto =
	 * (LoginDto)request.getSession().getAttribute("loginDto"); // 로그인이 되어있는지 확인
	 * if(!(loginDto == null)) { // 로그인이 되어 있을 경우 // 관리자 권한이 맞는지 확인
	 * if(loginDto.getPkAuthSeq() == 1) { // 관리자 권한인 경우
	 * menuService.menuManagement((MenuDto)model.getAttribute("menuManageDto"));
	 * return "/mypage.com"; } // 관리자 권한이 아닌 경우 return "/mypage.com"; } // 로그인이 되어있지
	 * 않은 경우 return "/main.com"; }
	 */

//	@RequestMapping(value="/getmenu.com", produces="application/json;charset=utf-8") /* value주소 이름*/
//	public String GetMenu(Model model, HttpServletRequest request) {
//		// 세션에서 내 정보를 가져온다
//		// LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
//		LoginDto testDto = new LoginDto();
//		testDto.setUserId("test");
//		testDto.setPassword("asdf");
//		LoginDto loginDto = loginService.checkLogin(testDto);
//		// 로그인이 되어있는지 확인
//		if(!(loginDto == null)) {
//			// 로그인이 되어있는 경우
//			List<MenuDto> menuList = menuService.getMenuList(loginDto.getPkAuthSeq());
//			System.out.println(menuList);
//			model.addAttribute("menuList", menuList);
//			return "redirect:/mypage.com";
//		} else {
//			// 로그인이 되어있지 않은 경우
//			return "/login.com";
//		}
//	}
	
	@RequestMapping(value="/Qna.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String Qna( Model model) {
	    return "content/qna";
	}

}
