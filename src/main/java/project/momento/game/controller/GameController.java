package project.momento.game.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.answer.service.AnswerService;
import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.AnswerToDB;
import project.momento.question.function.StringCodeCompile;
import project.momento.question.mapper.QuestionMapper;
import project.momento.question.service.QuestionService;
import project.momento.question.service.TestcaseService;
import project.momento.login.dto.LoginDto;

@Controller
public class GameController {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TestcaseService testcaseService;
	@Autowired
	private AnswerService answerService;
	
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
		
		mav.addObject("questionList", questionList);
		mav.setViewName("content/game"); // view 지정
		return mav;
	}
	
	@RequestMapping(value = "/singleGame", method=RequestMethod.POST)
	public ModelAndView singleGame(String level, String size) { 
		
		ModelAndView mav = new ModelAndView();
		QuestionDto qtDto = new QuestionDto();
		String[] levels = null;
		if(level.equals("4"))
			levels = new String[]{"1","2","3"};
		else
			levels = new String[]{level};
		qtDto.setLevels(levels);
		qtDto.setProbNum(Integer.parseInt(size));
		List<QuestionDto> questionList= questionService.selectQuestion(qtDto);
		System.out.println(questionList.size());
		int questionCount = questionList.size();
		while(questionCount < Integer.parseInt(size))
		{
			if(level.equals("1") || level.equals("3"))
			{
				level = "2";
			}
			else
			{
				level = "1";
			}

			levels = new String[]{level};
			qtDto.setLevels(levels);
			qtDto.setProbNum(Integer.parseInt(size)-questionCount);
			List<QuestionDto> questionList2 = questionService.selectQuestion(qtDto);
			questionList.addAll(questionList2);
			questionCount = questionList.size();
		}
		mav.addObject("questionList", questionList);
		mav.setViewName("content/game"); // view 지정
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/sendAnswer", produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public HashMap<String, String> test(@RequestParam Map<String, Object> param, HttpServletRequest request) {
		
		HashMap<String, String> answer = new HashMap<String, String>();
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		
		String userSeq = Integer.toString(loginDto.getPkUserSeq());
		String code = (String) param.get("format");
		String name = (String) param.get("name");
		int num = Integer.parseInt((String) param.get("number"));
		int pkUserSeq = loginDto.getPkUserSeq();

		System.out.println(" [+] " + code + " [+] " + name + " [+] " + num);
		
		List<TestcaseDto> testcaseDtos = testcaseService.selectTestcaseList(num);
		
													// 방넘버, 유저넘저, 함수명, 인풋list, 함수실행코드
		int result = StringCodeCompile.stringCodeCompile("0", userSeq, name, testcaseDtos, code);
		answer.put("answer", Integer.toString(result));
		System.out.println(" [+] " + result);
		// 문제번호, 유저번호, 코드, 결과, 문제타입
		AnswerToDB.answerToDB(num, pkUserSeq, code, result, questionService.selectQuestionSeq(num).getType(), answerService);
//		JavaOnlineCompilerApplication.stringCompileTest2();
		return answer;
	}
	
}
