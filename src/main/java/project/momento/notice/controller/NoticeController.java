package project.momento.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
//import project.momento.education.dto.EducationDto;
import project.momento.login.dto.LoginDto;
import project.momento.login.service.LoginService;
import project.momento.menu.dto.MenuDto;
import project.momento.menu.service.MenuService;
import project.momento.notice.dto.NoticeDto;
import project.momento.notice.service.NoticeService;
import project.momento.page.Criteria;
import project.momento.page.Paging;

@Controller
public class NoticeController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	@Autowired
	private NoticeService noticeService;

	/*
	 * 공지사항 관리 화면 이동
	 * return: content/mng/notice/noticeMain
	 */
	@RequestMapping(value="/{userDivn}/notice/main", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView noticeMain(@PathVariable String userDivn, Criteria cri, Model model, HttpServletRequest request) { 
		
		ModelAndView mv = new ModelAndView("content/mng/notice/noticeMain"); 
		int total = 0;
		total = noticeService.selectNoticeListCount(cri);
		// 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(total);
        List<NoticeDto> resultList = noticeService.selectNoticeList(cri);
		mv.addObject("resultList", resultList);
        mv.addObject("paging", paging);
		return mv;
		
	}
	
	@RequestMapping(value="/{userDivn}/notice/form", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView noticeForm(@PathVariable String userDivn, Criteria cri, Model model, NoticeDto noticeDto){
		ModelAndView mv = new ModelAndView("content/"+userDivn+"/notice/noticeForm"); 
		
		
		return mv;
		
	}
	
	@RequestMapping(value="/{userDivn}/notice/create", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String noticeSubmit(@PathVariable String userDivn, Criteria cri, Model model, NoticeDto noticeDto){
		
		System.out.println(noticeDto);
		noticeService.insertNotice(noticeDto);
		return "redirect:/"+userDivn+"/notice/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/getNoticeList", produces="application/json;charset=utf-8", method=RequestMethod.POST) /* value주소 이름*/
	public List<NoticeDto> getNoticeList(Criteria cri, Model model, HttpServletRequest request, NoticeDto noticeDto) {
		// 세션에서 내 정보를 가져온다
		LoginDto loginDto = (LoginDto)request.getSession().getAttribute("loginDto");
		List<NoticeDto> resultList = noticeService.selectNoticeList(cri);
		System.out.println(resultList);
		model.addAttribute("resultList", resultList);
		return resultList;			
	}
	
	
}
