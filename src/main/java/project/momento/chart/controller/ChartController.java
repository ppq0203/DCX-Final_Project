package project.momento.chart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.chart.dto.ChartDto;
import project.momento.chart.service.ChartService;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.page.Paging;

@Controller
public class ChartController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	
	 @Autowired 
	 private ChartService chartService;
	 
	/*
	 * chart사이트로 이동
	 */
	@RequestMapping(value="/chart.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public ModelAndView chartMain(Criteria cri, Model model, ChartDto chartDto) {
		ModelAndView mv = new ModelAndView("content/chart"); 
		int total = 0;
//		total = chartService.getProductListCount(cri);
		// 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(total); 
        System.out.println(paging);
        System.out.println(cri);
//		List<ChartDto> list = chartService.getProductList(cri);
//		mv.addObject("list", list);
		mv.addObject("paging", paging);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/chartUpload.com", produces="application/json;charset=utf-8", method = RequestMethod.POST)
	public String getProductList(
	    @RequestBody Map<String, Object> requestMap,
	    ChartDto chartDto,
	    HttpServletRequest request
	) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		int userSeq = loginDto.getPkUserSeq();
		// productArray = 저장한 상품 목록, chartArray = 저장한 두피 유형
		List<Integer> chartArray = (List<Integer>) requestMap.get("chartArray");
		/**
		 * 
		 * 
		 * 차트 정보 기입란.
		 * 
		 * **/
		List<String> productArray = (List<String>) requestMap.get("productArray");
	    // 차트를 db에 저장 후 해당 PK 넘버를 반환
	    chartService.insertResult(chartDto);
	    int resultSeq = chartDto.getReturnUserSeq();
	    
	    for (String prodSeq : productArray) {
	    	chartService.insertResultDetail(resultSeq, prodSeq, userSeq);
		}
	    

	    System.out.println(productArray);
	    System.out.println(chartArray);
	    
	    return "/mypage.com";
	}
	
//	@ResponseBody
//	@RequestMapping(value="/productList", produces="application/json;charset=utf-8")
//    public List<ChartDto> getProductList(ChartDto chartDto) {
//
//        List<ChartDto> list = null;
//        list = chartService.getProductList(chartDto);
//        return list;
//    }
//	
//	/*
//	 * 상품 클릭시 마이페이지로
//	 */
//	@PostMapping(value="/mypage.com", produces="application/text;charset=utf-8")
//	public String product( Model model,ChartDto chartDto) {
//		ChartService.userpage(chartDto);
//		return "content/mypage";
//	}
	
	/*
	 * 두피 검사 결과 확인버튼
	 */
//	@RequestMapping(value="/mypage.com", produces="application/text;charset=utf-8")
//	public String marksChart( Model gramp) {		
//		return "content/mypage"; 
//	}
	
}