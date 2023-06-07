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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class SignController {
	
	@Autowired
	private SignService SignService;
	
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
		int cnt = SignService.checkButton(signDto); // 중복확인한 값을 int로 받음
		model.addAttribute("cnt", cnt);
		return cnt;
	}

	/*
	 * 회원가입 등록
	 * param 상세정보
	 * return main page
	 */
	@RequestMapping(value="/{userDivn}/sign/form", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String goSignUp(@PathVariable String userDivn, Model model, SignDto signDto, HttpServletRequest request) throws IOException{
		
		String img = request.getParameter("imgPath");
		System.out.println(" [+] " + img);
		String path = "";
		if(img != "")
		{
			byte[] imageBytes = Base64.getDecoder().decode(img);
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
			Date now = new Date();
			path = signDto.getUserId() + now + request.getParameter("imgFile");
			
			//업로드 될 디렉토리 URL
//			ImageIO.write(bufImg, "png", new File("/img/this-should-be-linux-path/" + path));
		}
		
		signDto.setUserDivn(userDivn);
//		signDto.setImgPath(path);
		SignService.insertUser(signDto);
		return "redirect:/"+userDivn+"/login/main";
	}
	
}
