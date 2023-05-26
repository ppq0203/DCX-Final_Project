package project.momento.auth.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class AuthDto {
	
	/*권한 고유 식별번호*/
	private int pkAuthSeq;
	/*권한 코드(A: 관리자, B: 매니저, C:학생)*/
	private String authCode;

}