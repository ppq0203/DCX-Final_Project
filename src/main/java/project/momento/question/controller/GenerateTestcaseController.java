package project.momento.question.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVReader;

import project.momento.question.dto.TestcaseDto2;
import project.momento.question.function.StringCodeCompile;
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