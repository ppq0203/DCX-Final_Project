package project.momento.monopoly.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.momento.question.service.QuestionService;
import project.momento.question.dto.QuestionDto;

@Controller
public class MonopolyController {
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "marble.com", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView questionList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("content/marble");
		return mv;
	}
	
	@RequestMapping(value = "marbles.com", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ModelAndView selectQuestionList(QuestionDto questionDto) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> questionMap = new HashMap<String, Object>();
		questionMap = questionService.selectQuestionList(questionDto);
		mv.addObject("questionMap", questionMap);
		mv.setViewName("content/marbles");
		return mv;
	}
}
