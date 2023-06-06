package project.momento.subject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(value="/{userDivn}/{pkSubjectSeq}/subject/main", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String Subject(@PathVariable String userDivn, @PathVariable int pkSubjectSeq, HttpServletRequest request) {
		SubjectDto subjectDto = new SubjectDto();
		subjectDto = subjectService.getSubject(pkSubjectSeq);
		request.getSession().setAttribute("subjectDto", subjectDto);
		return "content/" + userDivn + "/subject/subject";
	}
	
}