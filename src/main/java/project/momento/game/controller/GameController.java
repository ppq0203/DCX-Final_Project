package project.momento.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.momento.question.dto.QuestionDto;
import project.momento.question.mapper.QuestionMapper;
import project.momento.question.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GameController {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionService QuestionService;
	
	@RequestMapping(value = "/game", method=RequestMethod.GET)
	public ModelAndView gameMain(String level, String questionNum) { 
		level = "4";
		questionNum = "36";
		
		ModelAndView mav = new ModelAndView();
		QuestionDto qtDto = new QuestionDto();
		String[] levels = null;
		if(level.equals("4"))
			levels = new String[]{"1","2","3"};
		else
			levels = new String[]{level};
		qtDto.setLevels(levels);
		qtDto.setProbNum(Integer.parseInt(questionNum));
		List<QuestionDto> questionList= QuestionService.selectQuestion(qtDto);
		System.out.println(questionList.size());
		mav.addObject("questionList", questionList);
		mav.setViewName("content/game"); // view 지정
		return mav;
	}
}
