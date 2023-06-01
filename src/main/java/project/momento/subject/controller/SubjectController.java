package project.momento.subject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
	@RequestMapping(value="/subject.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String selectSubject(Model model, SubjectDto subjectDto,HttpServletRequest request){
	int pkSubjectSeq = 1;//subjectDto.getPkSubjectSeq();
	//Dto에서 pkSubjectSeq를 가져와 subjectDto에 담는다.
	subjectDto.setPkSubjectSeq(pkSubjectSeq);
	//만약 pkSubjectSeq값이 0보다 크면 해당로직을 실행한다.
	if(pkSubjectSeq>0) {
		SubjectDto dto = subjectService.selectSubject(subjectDto);
//		List<SubjectDto> list = new ArrayList<>();
//	//DB에서 꺼내온 값을 변수 dto에 넣고 그 값을 변수 list에 담는다.	
//		list.add(dto);
//	//session을 호출하여 list라는 속성에 list값을 담아놓는다.	
//		request.getSession().setAttribute("list", list);
		model.addAttribute(dto)		;		}
		return "content/calendar";
																								}
								}
