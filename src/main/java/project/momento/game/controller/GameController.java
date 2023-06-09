package project.momento.game.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.StringCodeCompile;
import project.momento.question.mapper.QuestionMapper;
import project.momento.question.service.QuestionService;
import project.momento.question.service.TestcaseService;

@Controller
public class GameController {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TestcaseService testcaseService;
	
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
		List<QuestionDto> questionList= questionService.selectQuestion(qtDto);
		System.out.println(questionList.size());
		mav.addObject("questionList", questionList);
		mav.setViewName("content/game"); // view 지정
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/sendAnswer", produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public void test(@RequestParam Map<String, Object> param) {
		
		String code = (String) param.get("format");
		String name = (String) param.get("name");
		int num = Integer.parseInt((String) param.get("number"));
		
		System.out.println(" [+] " + code + " [+] " + name + " [+] " + num);
		
		
		List<TestcaseDto> testcaseDtos = testcaseService.selectTestcaseList(num);
		
													// 방넘버, 유저넘저, 함수명, 인풋list, 함수실행코드
		int result = StringCodeCompile.stringCodeCompile(0, 1, name, testcaseDtos, code);
		System.out.println(" [+] " + result);
//		JavaOnlineCompilerApplication.stringCompileTest2();
	}
	
}
