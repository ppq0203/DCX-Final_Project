package project.momento.mypage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.education.service.EducationService;
import project.momento.login.dto.LoginDto;
import project.momento.menu.service.MenuService;
import project.momento.subject.service.SubjectService;

@Controller
public class MypageController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	@Autowired
	private EducationService educationService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private MenuService menuService;

	/*
	 * 사용자 정보 화면 이동
	 * return: "redirect:/"+userDivn+"/profile/profile"
	 */
	@RequestMapping(value="/{userDivn}/profile", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String profileMain(@PathVariable String userDivn, Model model, HttpServletRequest request) { 
		return "content/"+userDivn+"/profile/profile";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getall.com", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public HashMap<String, List<Object>> GetMenu(Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		HashMap<String, List<Object>> listAll = new HashMap<String, List<Object>>();
		List<Object> subjectList;
		if (loginDto.getPkAuthSeq() == 3) {
			subjectList = educationService.getEducationList(loginDto.getPkUserSeq());
		} else {
			subjectList = educationService.getEducationListAll();
		}
		List<Object> menuList = menuService.getMenuList(loginDto.getPkAuthSeq());
		listAll.put("subjectList", subjectList);
		listAll.put("menuList", menuList);
		return listAll;			
	}
	
}
