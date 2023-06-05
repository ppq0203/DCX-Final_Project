package project.momento.testcase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.momento.question.function.TestcaseGenerator;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;
import project.momento.testcase.dto.TestcaseDto;
import project.momento.testcase.service.TestcaseService;

@Controller
public class TestcaseController {
	
	@Autowired
	private TestcaseService testcaseService;
	
	/*
	 * 학생 화면 페이지 이동
	 * param
	 * return contents/studentScreen 받는값
	 */
	@RequestMapping(value="/testcaseToDb", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String TestCaseToDb(Model model, List<TestcaseDto> testcaseList) {
		
		// DTO 하나씩 꺼내서 넣을 때
//		for (TestcaseDto testcaseDto : testcaseList) {
//			testcaseService.testCaseToDbDto(testcaseDto);
//        }
		// List로 집어 넣을 때 
		testcaseService.testCaseToDbList(testcaseList);
		return null;
	}
	
	@RequestMapping(value="/tg")
	public String tg()
	{
		TestcaseGenerator tg = new TestcaseGenerator();
		String csvPath = "question/sol3/sol/noInOut3.csv";
		List<TestcaseDto> testcaseList = tg.csvToDB(csvPath);
		for (TestcaseDto testcase : testcaseList)
		{
			testcaseService.testCaseToDbDto(testcase);
			
		}
		return "index";
	}
	
}