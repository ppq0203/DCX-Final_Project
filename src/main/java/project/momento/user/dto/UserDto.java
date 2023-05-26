package project.momento.user.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class UserDto {
	
	/*사용자 고유식별번호*/
	private int pkUserSeq;
	/*사용자 아이디*/
	private String userId;
	/*사용자 비밀번호*/
	private String password;
	/*사용자명*/
	private String name;
	/*사용자 휴대폰번호*/
	private String phone;
	/*사용자 생일*/
	private String birthday;
	/*주소*/
	private String address;
	/*상세주소*/
	private String detailedAddress;
	/*이메일*/
	private String email;
	/*사용유무(D: 삭제, H:휴면계정, Y:사용, N:미사용)*/
	private String useYn;
	/*가입한 날짜*/
	private String registDt;
	/*권한 고유 식별번호*/
	private int pkAuthSeq;

}