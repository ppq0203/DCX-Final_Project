package project.momento.sign.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.file.service.FileService;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class SignController {
	
	@Autowired
	private SignService signService;
	@Autowired
	private FileService fileService;
	
	/*
	 * 회원가입 페이지 이동
	 * param
	 * return contents/sign 받는값
	 */
	@RequestMapping(value="/{userDivn}/sign/main", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String goSignMain(@PathVariable String userDivn, Model model) {
	    return "content/"+userDivn+"/sign/sign";
	}
	
	//아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/signChk.com", produces="application/json;charset=utf-8")/* value는 겹치지 않게하기 */
	public int checkId(
			Model model
			, SignDto signDto  //필요한DTO 값불러오기,사용하기
			, @RequestParam("id") String id //html id 가져오기
			){// DTO=VO 같은말
		
		signDto.setUserId(id);
		int cnt = signService.checkButton(signDto); // 중복확인한 값을 int로 받음
		model.addAttribute("cnt", cnt);
		return cnt;
	}

	/*
	 * 회원가입 등록
	 * param 상세정보
	 * return main page
	 */
	@PostMapping(value="/{userDivn}/sign/form", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String goSignUp(@PathVariable String userDivn, SignDto signDto, Model model, HttpServletRequest request) throws IOException{	
		try {
			if (!(signDto.getImgFile().getOriginalFilename().trim().equals(""))) {
				System.out.println("이미지 있음");
				int imageFileSeq = fileService.uploadFile(signDto.getImgFile());
				signDto.setPkFileSeq(imageFileSeq);
			}
			if (userDivn.equals("mng")) {
				signService.insertManager(signDto);
			} else {
				signService.insertUser(signDto);
			}
			return "redirect:/"+userDivn+"/login/main";
		} catch (Exception e) {
			return "redirect:/"+userDivn+"/login/main";
		}
	}
	
}
