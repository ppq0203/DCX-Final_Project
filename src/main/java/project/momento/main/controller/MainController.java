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
import project.momento.main.dto.MainDto;
import project.momento.main.service.MainService;
import project.momento.question.dto.QuestionDto;
import project.momento.question.service.QuestionService;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class MainController {

	@Autowired
	private SignService signService;
	@Autowired
	private EducationService educationService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private MainService mainService;
	
	
	
	@RequestMapping(value = "/", produces = "application/text;charset=utf-8")
	public String Main(Model model, HttpServletRequest request) {
		
		return "content/main/userMain";
	}
	
	
	
	
	/*
	 * 
	 */
	@RequestMapping(value = "/{userDivn}/main", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String lmsMain(@PathVariable String userDivn, Model model, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		
		if (!(loginDto == null)) {
			EducationDto educationDto = new EducationDto();
			educationDto.setUserDivn(loginDto.getUserDivn());
			educationDto.setPkManagerSeq(loginDto.getPkManagerSeq());
			educationDto.setPkUserSeq(loginDto.getPkUserSeq());
			List<EducationDto> resultList = educationService.selectEducationStudList(educationDto);
			
			MainDto mainDto = new MainDto();
			mainDto.setPkUserSeq(loginDto.getPkUserSeq());
			
			MainDto gameResult = mainService.selectGameChart(mainDto);
	        calculateScoreRank(gameResult); // gameResult의 등급 계산
	        model.addAttribute("gameResult", gameResult);
	        request.getSession().setAttribute("Rank", gameResult.getColor());
	        
	        MainDto aiResult = mainService.selectAiChart(mainDto);
	        calculateScoreRank(aiResult); // aiResult의 등급 계산
	        model.addAttribute("aiResult", aiResult);
	        
	        MainDto examResult = mainService.selectExamChart(mainDto);
	        calculateScoreRank(examResult); // examResult의 등급 계산
	        model.addAttribute("examResult", examResult);
			
			model.addAttribute("list", resultList);
			return "content/" + userDivn + "/main/main";
		} else {
			return "redirect:/" + userDivn + "/login/main";
		}
	}
	private void calculateScoreRank(MainDto dto) {
	    double maxVal = dto.getMaxVal();
	    double minVal = dto.getMinVal();
	    double userVal = dto.getUserVal();
	    double avgVal = dto.getAvgVal();
	    
	    double ratio = (userVal - minVal) / (maxVal - minVal);
	    
	    if (ratio >= 0.9) {
	        dto.setColor("#dc143c");
	    } else if (ratio >= 0.8) {
	        dto.setColor("#4b0082");
	    } else if (ratio >= 0.7) {
	        dto.setColor("#8a2be1");
	    } else if (ratio >= 0.6) {
	        dto.setColor("#7fffd4");
	    } else if (ratio >= 0.5) {
	        dto.setColor("#ffd700");
	    } else {
	        dto.setColor("#d2b48c");
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
	public Map<String, MainDto> ChartList(@PathVariable int pkUserSeq, Model model, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		MainDto mainDto = new MainDto();
		mainDto.setPkUserSeq(pkUserSeq);
		
		Map<String, MainDto> result = new HashMap<>();

		MainDto gameResult = mainService.selectGameChart(mainDto);
		result.put("gameResult", gameResult);
		
		MainDto aiResult = mainService.selectAiChart(mainDto);
		result.put("aiResult", aiResult);
		
		MainDto examResult = mainService.selectExamChart(mainDto);
		result.put("examResult", examResult);

		return result;
	}
	

	/*
	 * 학생 화면 페이지 이동 param return contents/studentScreen 받는값
	 * @RequestMapping(value="/studentMain.com",
	 * produces="application/text;charset=utf-8") value주소 불러오기 이름 public String
	 * studetMain( Model model) { return "content/studentMain"; }
	 */

}
