package project.momento.question.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.TestcaseGenerator;
import project.momento.question.service.TestcaseService;

@Controller
public class TestcaseController {
	
	@Autowired
	private TestcaseService testcaseService;
	
	
//	//참고코드
//	void temp()
//	{
//		List<TestcaseDto> testcaseList = new ArrayList<TestcaseDto>();
//		TestcaseDto testcase1 = new TestcaseDto();
//		testcase1.setPkQuestionSeq(1);
//		testcase1.setInput("input1");
//		testcase1.setOutput("output1");
//		testcaseList.add(testcase1);
//		
//		TestcaseDto testcase2 = new TestcaseDto();
//		testcase2.setPkQuestionSeq(1);
//		testcase2.setInput("input2");
//		testcase2.setOutput("output2");
//		testcaseList.add(testcase2);
//		
//		// DTO 하나씩 꺼내서 넣을 때
//		for (TestcaseDto testcaseDto : testcaseList) {
//			testcaseService.testCaseToDbDto(testcaseDto);
//		}		
//		// List로 집어 넣을 때 
//		testcaseService.testCaseToDbList(testcaseList);
//	}
//	// 테스트케이스 생성하는 함수실행
//	@RequestMapping(value = "/make/testcase")
//    public String makeTestcase() {
//		// 테스트케이스 생성기
////		TestcaseGenerator.testcaseGenrate();
//        return "index";
//	}
	
	// csv로 생성되어있는 테스트케이스를 DB에 넣는 함수실행
	@RequestMapping(value="/tg")
	public String tg()
	{
		String csvPath = "question/sol3/sol/noInOut3.csv";
		List<TestcaseDto> testcaseList = TestcaseGenerator.csvToDB(csvPath);
		// 실제로 데이터를 db에 넣는 함수 이미 넣었기때문에 주석처리해두었음
//		for (TestcaseDto testcase : testcaseList)
//		{
//			testcaseService.testCaseToDbDto(testcase);
//		}
		return "index";
	}
}