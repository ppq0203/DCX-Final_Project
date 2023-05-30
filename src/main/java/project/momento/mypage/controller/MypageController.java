package project.momento.mypage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import project.momento.chart.dto.ChartDto;
import project.momento.chart.service.ChartService;
import project.momento.login.dto.LoginDto;
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
	private ChartService chartService;
	private MenuService menuService;

	/*
	 * 사이트에서 이동
	 */
	@RequestMapping(value="/mypage.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView Mypage(Criteria cri, Model model, HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("content/mypage");
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		cri.setPkUserSeq(loginDto.getPkUserSeq());
		int auth = loginDto.getPkAuthSeq();
		if(auth != 3)
		{
			mv.addObject("role", "Admin");
		}
		else
		{
			mv.addObject("role", "Student");
		}
		int total = 1;
//		total = chartService.getProductDetailListCount(cri);
//		List<ChartDto> list = chartService.getResultList(cri);
		// 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(total); 
        System.out.println(paging);
        System.out.println(cri);
//		List<ChartDto> detailList = chartService.getProductDetailList(cri);
		
//		model.addAttribute("list", list);
//		mv.addObject("detailList", detailList);
		mv.addObject("paging", paging);   
		return mv;
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
				return "/mypage.com";
			}
			// 관리자 권한이 아닌 경우
			return "/mypage.com";
		}
		// 로그인이 되어있지 않은 경우
		return "/main.com";
	}
	
	@RequestMapping(value="/getmenu.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String GetMenu(Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		// 로그인이 되어있는지 확인
		if(!(loginDto == null)) {
			// 로그인이 되어있는 경우
			List<MenuDto> menuList = menuService.getMenuList(loginDto.getPkAuthSeq());
			model.addAttribute("menuList", menuList);
			return "/mypage.com";
		}
		// 로그인이 되어있지 않은 경우
		return "/main.com";
	}
	
	@RequestMapping(value = "sign/update", produces="application/text;charset=utf-8")
	public String signUpdate(LoginDto loginDto, HttpServletRequest request) {
		LoginDto login = (LoginDto) request.getSession().getAttribute("loginDto");
		
		if(login != null)
		{
			//현재 로그인 된 계정이 관리자인지학생인지 체크.
			if(login.getPkAuthSeq() != 1)
			{
				//학생일 경우.
				return "content/studentScreen";
			}
			//관리자인 경우.
			return "content/signManage";
		}
		return "content/login";
		
	}
	
}
