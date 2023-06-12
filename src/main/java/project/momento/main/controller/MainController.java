package project.momento.main.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.education.dto.EducationDto;
import project.momento.education.service.EducationService;
import project.momento.login.dto.LoginDto;
import project.momento.question.dto.QuestionDto;
import project.momento.question.service.QuestionService;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class MainController {

	@Autowired
	private SignService SignService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private QuestionService questionService;
	
	/*
	 * 
	 */
	@RequestMapping(value = "/{userDivn}/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String Main(@PathVariable String userDivn, Model model, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		
		if (!(loginDto == null)) {
			EducationDto educationDto = new EducationDto();
			educationDto.setUserDivn(loginDto.getUserDivn());
			educationDto.setPkManagerSeq(loginDto.getPkManagerSeq());
			educationDto.setPkUserSeq(loginDto.getPkUserSeq());
			List<EducationDto> resultList = educationService.selectEducationStudList(educationDto);
			
			model.addAttribute("list", resultList);
			return "content/" + userDivn + "/main/main";
		} else {
			return "redirect:/" + userDivn + "/login/main";
		}
	}
	
	/*
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/calendar/list", produces = "application/json;charset=utf-8") /* value주소 이름 */
	public List<EducationDto> CalendarList(Model model, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		EducationDto educationDto = new EducationDto();
		educationDto.setUserDivn(loginDto.getUserDivn());
		educationDto.setPkManagerSeq(loginDto.getPkManagerSeq());
		educationDto.setPkUserSeq(loginDto.getPkUserSeq());
		List<EducationDto> resultList = educationService.selectSubjectList(educationDto);
		return resultList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/chart/list/{pkUserSeq}", produces = "application/json;charset=utf-8") /* value주소 이름 */
	public Map<String, List<QuestionDto>> ChartList(@PathVariable int pkUserSeq, Model model, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		QuestionDto questionDto = new QuestionDto();
		questionDto.setPkUserSeq(pkUserSeq);
		
		Map<String, List<QuestionDto>> resultList = new HashMap<>();

		//List<QuestionDto> gameResultList = questionService.selectGameChartList(questionDto);
		//resultList.put("gameResultList", gameResultList);

		
		
		return resultList;
	}
	

	/*
	 * 학생 화면 페이지 이동 param return contents/studentScreen 받는값
	 * @RequestMapping(value="/studentMain.com",
	 * produces="application/text;charset=utf-8") value주소 불러오기 이름 public String
	 * studetMain( Model model) { return "content/studentMain"; }
	 */

}
