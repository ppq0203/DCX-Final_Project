package project.momento.img.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import project.momento.sign.dto.SignDto;
import project.momento.sign.service.SignService;

@Controller
public class ImgController {
	
	/*
	 * @Autowired private SignService SignService;
	 */
	
	@RequestMapping(value="/img.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String imgMain( Model model) {
	    return "content/imgup";
	}
	
	@RequestMapping(value="/imgPredict.com", produces="application/text;charset=utf-8") /* value주소 이름*/
	public String imgPredict( Model model) {
	    
		 // Flask 서버의 URL을 입력합니다.
        String url = "http://127.0.0.1:5000/predict?image_path=C:\\Users\\anyware\\Downloads\\2020-02-148.유형별두피_sample (1)\\원천데이터\\6.탈모\\1.경증\\9905_A2LEBJJDE00042M_1608611619096_4_LH.jpg";

        // HTTP 요청 헤더를 설정합니다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HTTP 요청을 생성합니다.
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        // RestTemplate 객체를 생성합니다.
        RestTemplate restTemplate = new RestTemplate();

        // HTTP GET 요청을 전송하고, 응답을 문자열 형태로 받아옵니다.
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        // HTTP 응답을 출력합니다.
        System.out.println("HTTP Status: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
		
		return "content/imgup";
	}


	
	
}
