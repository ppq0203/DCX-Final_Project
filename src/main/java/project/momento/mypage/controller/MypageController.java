package project.momento.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.menu.service.MenuService;
import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.sign.dto.SignDto;

@Controller
public class MypageController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private LoginService loginService;

	/*
	 * 사이트에서 이동
	 */
	@RequestMapping(value="/mypage.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String Mypage(Criteria cri, Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		// 로그인이 되어있는지 확인
		if(!(loginDto == null)) {
			// 로그인이 되어있는 경우
			List<MenuDto> menuList = menuService.getMenuList(loginDto.getPkAuthSeq());
			System.out.println(menuList);
			model.addAttribute("menuList", menuList);
			//현재 로그인 된 계정이 관리자인지학생인지 체크.
			if(loginDto.getPkAuthSeq() != 1)
			{
				//학생일 경우.
				return "content/studentScreen";
			}
			//관리자인 경우.
			return "content/signManage";
		}
		// 로그인이 되어있지 않은 경우
		return "redirect:/login.com";
	}
	
	@RequestMapping(value="/menumanagement.com", produces="application/text;charset=utf-8")
	public String MenuManagement(Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		// 로그인이 되어있는지 확인
		if(!(loginDto == null)) {
			// 로그인이 되어 있을 경우
			// 관리자 권한이 맞는지 확인
			if(loginDto.getPkAuthSeq() == 1) {
				// 관리자 권한인 경우
				menuService.menuManagement((MenuDto)model.getAttribute("menuManageDto"));
				return "redirect:/mypage.com";
			}
			// 관리자 권한이 아닌 경우
			return "redirect:/main.com";
		}
		// 로그인이 되어있지 않은 경우
		return "redirect:/login.com";
	}
	
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
	
}
