package project.momento.subject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//<<<<<<< HEAD
import jakarta.servlet.http.HttpServletRequest;
//=======
import project.momento.page.Criteria;
import project.momento.page.Paging;
//>>>>>>> refs/remotes/origin/LMS_all
import project.momento.subject.dto.SubjectDto;
import project.momento.subject.service.SubjectService;

@Controller
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
//<<<<<<< HEAD
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
//								}
//=======
	@RequestMapping(value="/mng/subject/main", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectMain(Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/mng/subject/subjectMain"); 
		int total = 0;
//		total = chartService.getProductListCount(cri);
		// 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(total); 
        System.out.println(paging);
        System.out.println(cri);
        mv.addObject("paging", paging);
		return mv;
	}
	
	@RequestMapping(value="/mng/subject/form", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView subjectForm(Criteria cri, Model model, SubjectDto subjectDto){
		ModelAndView mv = new ModelAndView("content/mng/subject/subjectForm"); 
		return mv;
	}
}
//>>>>>>> refs/remotes/origin/LMS_all
