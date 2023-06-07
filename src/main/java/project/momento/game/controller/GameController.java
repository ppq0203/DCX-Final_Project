package project.momento.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.momento.question.dto.QuestionDto;
import project.momento.question.mapper.QuestionMapper;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GameController {
	@Autowired
	private QuestionMapper questionMapper;
	
	@RequestMapping(value = "/game", method=RequestMethod.GET)
	public ModelAndView gameMain(HttpServletRequest request) { 
		ModelAndView mav = new ModelAndView();
		List<QuestionDto> salaryList = questionMapper.selectQuestionList();
		mav.addObject("salaryList", salaryList);
		mav.setViewName("content/game"); // view 지정
		return mav;
	}
}
