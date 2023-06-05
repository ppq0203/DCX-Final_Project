package project.momento.test.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;

import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.StringCodeCompile;
import project.momento.question.service.QuestionService;

@RestController
public class JavaOnlineCompilerApplication {

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "/compiletest")
    public String stringCompileTest() {
		// 폴더 경로
		String code = "//We check whether we are at the diagonal or not using a boolean and increment the pointer accordingly.\r\n"
				+ "\r\n"
				+ "public class Solution {\r\n"
				+ "\r\n"
				+ "    public String convert(String s, int row) {\r\n"
				+ "        if (row == 1) return s;\r\n"
				+ "        StringBuilder sb = new StringBuilder();\r\n"
				+ "        for (int i = 0; i < row; i++) {\r\n"
				+ "            int j = i;\r\n"
				+ "            if (i == 0 || i == row - 1) {\r\n"
				+ "                while (j < s.length()) {\r\n"
				+ "                    sb.append(s.charAt(j));\r\n"
				+ "                    j += 2 * (row - 1);\r\n"
				+ "                }\r\n"
				+ "            } else {\r\n"
				+ "                boolean diagonal = false;\r\n"
				+ "                while (j < s.length()) {\r\n"
				+ "                    if (!diagonal) {\r\n"
				+ "                        sb.append(s.charAt(j));\r\n"
				+ "                        j += 2 * (row - i - 1);\r\n"
				+ "                        diagonal = true;\r\n"
				+ "                    } else {\r\n"
				+ "                        sb.append(s.charAt(j));\r\n"
				+ "                        j += 2 * i;\r\n"
				+ "                        diagonal = false;\r\n"
				+ "                    }\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "        return sb.toString();\r\n"
				+ "    }\r\n"
				+ "}\r\n"
				+ "";
		List<TestcaseDto> testcaseDtos = this.csvToInput("question/csv/num6sol.csv");
//		System.out.println(testcaseDtos.size());
		int result = StringCodeCompile.stringCodeCompile(0, 0, "convert", testcaseDtos, code);
		System.out.println(result);
        return "index";
	}
	
	public List<TestcaseDto> csvToInput(String csvPath)
	{
		List<TestcaseDto> inputDataList = new ArrayList<TestcaseDto>();
		TestcaseDto solDto = null;
		try {
			// input값으로 넣을 데이터들 csv파일로 load
			CSVReader reader = new CSVReader(new FileReader(csvPath));
            String [] nextLine;
			// 데이터 한행씩 로드
            while ((nextLine = reader.readNext()) != null) {   // 2
            	solDto = new TestcaseDto();
                // input.csv에는 input 하나만 들어있기때문에 nextLine[0]만 수행함
            	if (Integer.parseInt(nextLine[0]) == 6)
            	{
            		solDto.setPkQuestionSeq(Integer.parseInt(nextLine[0]));
            		solDto.setInput(nextLine[1]);
            		solDto.setOutput(nextLine[2]);
            		inputDataList.add(solDto);
            	}
            }
		} catch (IOException e) {
			// CSVReader에서 오류 발생했을때,
//			e.printStackTrace();
		} // 1
		return inputDataList;
	}
	
	@RequestMapping(value = "/compiletest2")
    public String stringCompileTest2() {
		// 폴더 경로
		String code = "//We check whether we are at the diagonal or not using a boolean and increment the pointer accordingly.\r\n"
				+ "\r\n"
				+ "public class Solution {\r\n"
				+ "\r\n"
				+ "    public String convert(String s, int row) {\r\n"
				+ "        if (row == 1) return s;\r\n"
				+ "        StringBuilder sb = new StringBuilder();\r\n"
				+ "        for (int i = 0; i < row; i++) {\r\n"
				+ "            int j = i;\r\n"
				+ "            if (i == 0 || i == row - 1) {\r\n"
				+ "                while (j < s.length()) {\r\n"
				+ "                    sb.append(s.charAt(j));\r\n"
				+ "                    j += 2 * (row - 1);\r\n"
				+ "                }\r\n"
				+ "            } else {\r\n"
				+ "                boolean diagonal = false;\r\n"
				+ "                while (j < s.length()) {\r\n"
				+ "                    if (!diagonal) {\r\n"
				+ "                        sb.append(s.charAt(j));\r\n"
				+ "                        j += 2 * (row - i - 1);\r\n"
				+ "                        diagonal = true;\r\n"
				+ "                    } else {\r\n"
				+ "                        sb.append(s.charAt(j));\r\n"
				+ "                        j += 2 * i;\r\n"
				+ "                        diagonal = false;\r\n"
				+ "                    }\r\n"
				+ "                }\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "        return sb.toString();\r\n"
				+ "    }\r\n"
				+ "}\r\n"
				+ "";
		List<TestcaseDto> testcaseDtos = questionService.selectTestcaseList(6);
		for (TestcaseDto t : testcaseDtos)
		{
//			System.out.println(t.getInput().equals("3,3;6"));
		}
//		System.out.println(testcaseDtos.size());
		int result = StringCodeCompile.stringCodeCompile(0, 0, "convert", testcaseDtos, code);
		System.out.println(result);
        return "index";
	}
}