package project.momento.question.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.question.function.TestcaseGenerator;

@Controller
public class GenerateTestcaseController {
	@RequestMapping(value = "/make/testcase")
    public String makeTestcase() {
		// 폴더 경로
		TestcaseGenerator.testcaseGenrate();
        return "index";
	}
}