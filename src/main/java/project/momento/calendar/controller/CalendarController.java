package project.momento.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import project.momento.chart.dto.ChartDto;
import project.momento.chart.service.ChartService;
import project.momento.page.Criteria;

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
	@RequestMapping(value="/std/calendar", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView calendarMain(Criteria cri, Model model, ChartDto chartDto) {
		ModelAndView mv = new ModelAndView("content/std/calendar/calendar"); 
		return mv;
	}
	
}