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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.education.dto.EducationDto;
import project.momento.exam.dto.ExamDto;
import project.momento.exam.service.ExamService;
import project.momento.file.service.FileService;
import project.momento.login.dto.LoginDto;
import project.momento.notice.dto.NoticeDto;
import project.momento.page.Criteria;
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
	@RequestMapping(value = "/{userDivn}/{pkSubjectSeq}/exam/main", produces = "application/text;charset=utf-8")
	public String examMain(@PathVariable String userDivn, @PathVariable int pkSubjectSeq, HttpServletRequest request,
			Model model) {
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		request.getSession().setAttribute("subjectDto", subjectDto);
		ExamDto examDto = new ExamDto();
		examDto.setUserDivn(userDivn);
		examDto.setPkSubjectSeq(pkSubjectSeq);
		examDto.setPkUserSeq(loginDto.getPkUserSeq());
		List<ExamDto> exameList = examService.getExamList(examDto);
		List<ExamDto> exameResultList = examService.getExamResultList(examDto);
		request.getSession().setAttribute("tab", 4);
		model.addAttribute("exameList", exameList);

		return "content/" + userDivn + "/exam/exam";
	}

	@PostMapping(value = "/{userDivn}/exam/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String examCreate(@RequestParam("imgPath") List<MultipartFile> files,
			@RequestParam("title") List<String> titles, @RequestParam("contents") List<String> contents,
			@PathVariable String userDivn, HttpServletRequest request) {
		request.getSession().setAttribute("tab", 5);
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		try {

			ExamDto examDto = new ExamDto();
			examDto.setPkSubjectSeq(subjectDto.getPkSubjectSeq());
			examService.insertExam(examDto);
			int returnId = examDto.getReturnId();
			for (int i = 0; i < titles.size(); i++) {
				ExamDto examDto2 = new ExamDto();
				MultipartFile file = files.get(i);
				String title = titles.get(i);
				String content = contents.get(i);

				if (!file.isEmpty()) {
					int filePath = fileService.uploadFile(file);
					examDto2.setPkFileSeq(filePath);
					examDto2.setPkSubjectSeq(subjectDto.getPkSubjectSeq());
					examDto2.setPkExamSeq(returnId);
					examDto2.setTitle(title);
					examDto2.setContents(content);
					examService.insertExamDetail(examDto2);
				} else {
					examDto2.setPkSubjectSeq(subjectDto.getPkSubjectSeq());
					examDto2.setPkExamSeq(returnId);
					examDto2.setTitle(title);
					examDto2.setContents(content);
					examService.insertExamDetail(examDto2);
				}
			}
			return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/exam/main";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/exam/main";
		}
	}
	
	
	@RequestMapping(value = "/{userDivn}/exam/result", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String examResult(@PathVariable String userDivn, Criteria cri, Model model, ExamDto examDto, HttpServletRequest request) {
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		String[] answers = examDto.getAnswer().split(",");
		for(String answer : answers) {
			examDto.setAnswer(answer);
			examDto.setPkUserSeq(loginDto.getPkUserSeq());
			examService.insertResult(examDto);
		}
			
		
		
		return "redirect:/" + userDivn + "/"+pkSubjectSeq+"/exam/main";
	}
	
	@RequestMapping(value = "/{userDivn}/exam/delete", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String examDelete(@PathVariable String userDivn, Criteria cri, Model model, ExamDto examDto, HttpServletRequest request) {
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		
		examService.deleteExamDetail(examDto);
		examService.deleteExam(examDto);
		
		return "redirect:/" + userDivn + "/"+pkSubjectSeq+"/exam/main";
	}
	
	@RequestMapping(value = "/{userDivn}/exam/start", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String examStart(@PathVariable String userDivn, @RequestParam int pkExamSeq, Criteria cri, Model model, HttpServletRequest request) {
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		ExamDto examDto = new ExamDto();
		examDto.setPkExamSeq(pkExamSeq);
		examService.startExam(examDto);
		
		return "redirect:/" + userDivn + "/"+pkSubjectSeq+"/exam/main";
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/getExamDetailList", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public List<ExamDto> GetEducationList(@RequestParam int pkExamSeq, Criteria cri, Model model,
			HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		ExamDto examDto = new ExamDto();
		examDto.setPkExamSeq(pkExamSeq);
		List<ExamDto> resultList = examService.selectExamDetailList(examDto);
		model.addAttribute("resultList", resultList);
		return resultList;
	}

}