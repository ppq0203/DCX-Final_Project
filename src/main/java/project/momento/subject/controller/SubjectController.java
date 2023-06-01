package project.momento.subject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
	@RequestMapping(value="/subject.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String selectSubject(Model model, SubjectDto subjectDto){
//		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		//Dto에서 pkSubjectSeq를 가져와 subjectDto에 담는다.
		subjectDto.setPkSubjectSeq(1);
//		if(1>0) {
			subjectService.selectSubject(subjectDto);
			System.out.println(subjectDto);
//			SubjectDto dto = subjectService.selectSubject(subjectDto);
//			List<SubjectDto> list = new ArrayList<>();
//			for (int i = 0; i < list.size(); i++) {
//				list.add(dto);
//				model.addAttribute("subjectList", list);
//													}
//			System.out.println(model);
//							}
		
		

		
		return "content/calendar";
	}
}
