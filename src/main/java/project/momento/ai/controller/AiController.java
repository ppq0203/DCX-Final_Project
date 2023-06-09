package project.momento.ai.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.exam.dto.ExamDto;
import project.momento.login.dto.LoginDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;
import project.momento.subject.dto.SubjectDto;

@Controller
public class AiController {

	@Autowired
	private SignService SignService;

	/*
	 * AI Main 화면 이동
	 */
	@RequestMapping(value = "/{userDivn}/ai/main", produces = "application/text;charset=utf-8")
	public String aiMain(@PathVariable String userDivn, HttpServletRequest request, Model model) {

		return "content/" + userDivn + "/ai/article";
	}

}