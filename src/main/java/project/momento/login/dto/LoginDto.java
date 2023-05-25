package project.momento.login.dto;
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class LoginDto {
	
	private int pkUserSeq;
	/*아이디*/
	private String userId;
	/*비밀번호*/
	private String userPassword;
	/*이름*/
	private String userNm;
	/*전화번호*/
	private String userPhone;
	/*이메일*/
	private String userEmail;

}