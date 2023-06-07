package project.momento.exam.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.momento.exam.dto.ExamDto;
import project.momento.exam.service.ExamService;
import project.momento.file.service.FileService;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;
import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class ExamController {

	@Autowired
	private ExamService examService;
	@Autowired
	private FileService fileService;
	@Autowired
	private SubjectService subjectService;
	
	/*
	 * 테스트 관리 화면 이동 param return contents/sign 받는값
	 */
	@RequestMapping(value = "/{userDivn}/exam/main", produces = "application/text;charset=utf-8") /* value주소 불러오기 이름 */
	public String examMain(@PathVariable String userDivn, Model model) {
		return "content/" + userDivn + "/exam/exam";
	}

	@PostMapping(value = "/mng/exam/create", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	public String handleFormData(@RequestPart("files") MultipartFile file, @RequestPart("titles") String title,
			@RequestPart("contents") String contents) {
		try {
			int filePath = fileService.uploadFile(file);
			ExamDto examDto = new ExamDto();
			examDto.setPkFileSeq(filePath);
			examDto.setTitle(title);
			examDto.setContents(contents);
			examService.insertExam(examDto);
			return null;
		} catch (Exception e) {
			// 처리 중 예외가 발생했을 경우
			e.printStackTrace();
			return null;
		}
	}

}