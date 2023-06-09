package project.momento.sign.dto;
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class SignDto {
	
	/*사용자 고유식별번호*/
	private int pkUserSeq;
	/*권한 고유 식별번호*/
	private int pkAuthSeq;
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
	/*유저 구분(mng: 관리자, std: 학생)*/
	private String userDivn;
	/*사용유무(D: 삭제, H:휴면계정, Y:사용, N:미사용)*/
	private String useYn;
	/*가입한 날짜*/
	private String registDt;
	/*수강 상태(N: 퇴소, Y: 수강중, E: 수료)*/
	private String classState;
	
	private int pkFileSeq;
	
	private MultipartFile imgFile;
}