package project.momento.qna.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;
import project.momento.qna.dto.QnaDto;
import project.momento.qna.service.QnaService;
import project.momento.subject.dto.SubjectDto;

@Controller
public class QnaController {

	/*
	 * @Autowired private SignService SignService;
	 */

	@Autowired
	private QnaService qnaService;

	/*
	 * Q&A 페이지 이동
	 * param
	 * return contents/userDivn/qna/qna 받는값
	 */
	@RequestMapping(value="/{userDivn}/qna/main", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public ModelAndView qnasMain(@PathVariable String userDivn, Model model, Criteria cri, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("content/"+userDivn+"/qna/qna");
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		
		if (userDivn.equals("mng")) {			
			int total = qnaService.getQnaCountForMng(subjectDto.getPkSubjectSeq());
//		total = chartService.getProductListCount(cri);
			// 페이징 객체
			Paging paging = new Paging();
			paging.setCri(cri);
			paging.setTotalCount(total);
			subjectDto.setCri(cri);
			List<QnaDto> qnaList = qnaService.getQnaListForMng(subjectDto);
			mv.addObject("qnaList", qnaList);
			mv.addObject("cri", cri);
			mv.addObject("paging", paging);
			return mv;
		} else {
			int total = qnaService.getQnaCountForUser(subjectDto.getPkSubjectSeq(), loginDto.getPkUserSeq());
			Paging paging = new Paging();
			paging.setCri(cri);
			paging.setTotalCount(total);
			subjectDto.setCri(cri);
			subjectDto.setPkUserSeq(loginDto.getPkUserSeq());
			List<QnaDto> qnaList = qnaService.getQnaListForUser(subjectDto);
			mv.addObject("qnaList", qnaList);
			mv.addObject("cri", cri);
			mv.addObject("paging", paging);
			return mv;
		}
	}
	
	@RequestMapping(value="/{userDivn}/qna/create", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String insertQna(@PathVariable String userDivn, Model model, QnaDto qnaDto) {
		qnaService.insertQna(qnaDto);
		System.out.println(qnaDto);
		return "redirect:/std/qna/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/openAskInfo", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public QnaDto openAskInfo(@RequestBody HashMap<String, Object> map, Model model, HttpServletRequest request) {
		// 세션에서 내 정보를 가져온다
		String pkAskSeq = (String) map.get("pkAskSeq");
		QnaDto qnaDto = qnaService.selectAsk(pkAskSeq);
		return qnaDto;
	}

}
