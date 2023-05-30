package project.momento.calendar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.chart.dto.ChartDto;
import project.momento.chart.service.ChartService;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;

@Controller
public class CalendarController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	 @Autowired 
	 private ChartService chartService;
	 
	/*
	 * calendar사이트로 이동
	 */
	@RequestMapping(value="/calendar.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView calendarMain(Criteria cri, Model model, ChartDto chartDto) {
		ModelAndView mv = new ModelAndView("content/calendar"); 
		return mv;
	}
	
}