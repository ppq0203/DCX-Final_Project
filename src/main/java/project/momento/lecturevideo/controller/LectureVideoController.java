package project.momento.lecturevideo.controller;

import java.io.IOException;
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

import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class LectureVideoController {
	
	@Autowired
	private SignService SignService;
	
	/*
	 * 학생 화면 페이지 이동
	 * param
	 * return contents/studentScreen 받는값
	 */
	@RequestMapping(value="/lecturevideo.com", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String LectureVideo( Model model) {
	    return "content/LectureVideo";
	}
	
}