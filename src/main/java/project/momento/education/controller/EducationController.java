package project.momento.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.education.dto.EducationDto;
import project.momento.education.service.EducationService;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;

@Controller
public class EducationController {

	@Autowired
	private EducationService educationService;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/{userDivn}/education/main/{pkEducationSeq}", produces = "application/text;charset=utf-8") /*
																														 * value주소
																														 * 이름
																														 */
	public ModelAndView educationLectMain(@PathVariable String userDivn, @PathVariable int pkEducationSeq, Criteria cri,
			Model model, EducationDto educationDto) {
		ModelAndView mv = new ModelAndView("content/" + userDivn + "/education/education");
		int total = 0;
//		total = chartService.getProductListCount(cri);
		// 페이징 객체
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		System.out.println(paging);
		System.out.println(cri);
		mv.addObject("paging", paging);
		return mv;
	}

	@RequestMapping(value = "/{userDivn}/education/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public ModelAndView educationMain(@PathVariable String userDivn, Criteria cri, Model model,
			EducationDto educationDto) {
		ModelAndView mv = new ModelAndView("content/" + userDivn + "/education/educationMain");
		int total = 0;
		total = educationService.selectEducationCount(cri);
		// 페이징 객체
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		System.out.println(paging);
		System.out.println(cri);
		List<LoginDto> managerList = loginService.selectManagerList();
		List<LoginDto> userList = loginService.selectUserList();
		List<EducationDto> educationList = educationService.selectEducationList(cri);
		mv.addObject("userList", userList);
		mv.addObject("educationList", educationList);
		mv.addObject("managerList", managerList);
		mv.addObject("paging", paging);
		return mv;
	}

	@RequestMapping(value = "/mng/education/form", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public ModelAndView educationForm(Criteria cri, Model model, EducationDto educationDto) {
		ModelAndView mv = new ModelAndView("content/mng/education/educationMain");
		return mv;
	}

	@RequestMapping(value = "/mng/education/create", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String educationCreate(Criteria cri, Model model, EducationDto educationDto) {
		ModelAndView mv = new ModelAndView("content/mng/education/educationMain");
		System.out.println(educationDto);
		return "redirect:/mng/education/main";
	}

	@ResponseBody
	@RequestMapping(value = "/getEducationList", produces = "application/json;charset=utf-8", method = RequestMethod.POST) /*
																															 * value주소
																															 * 이름
																															 */
	public List<EducationDto> GetEducationList(Criteria cri, Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		List<EducationDto> resultList = educationService.selectEducationList(cri);
		model.addAttribute("resultList", resultList);
		return resultList;
	}

	@ResponseBody
	@RequestMapping(value = "/getsubjectList", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public Map<String, List<EducationDto>> getSubjectList(@RequestParam int pkEducationSeq, Criteria cri, Model model,
			HttpServletRequest request) {
		
		Map<String, List<EducationDto>> resultList = new HashMap<>();
		EducationDto educationDto = new EducationDto();
		educationDto.setPkEducationSeq(pkEducationSeq);

		List<EducationDto> educationList = educationService.selectEducation(educationDto);
		resultList.put("educationList", educationList);

		List<EducationDto> subjectList = educationService.selectSubjectList(pkEducationSeq);
		resultList.put("subjectList", subjectList);
		
		model.addAttribute("resultList", resultList);

		return resultList;
	}

}
