package project.momento.aiQuiz.dto;
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/* AiDict */
public class AiDictDto extends LoginDto {
	
	/* 용어 고유 식별번호*/
	private int pkDictionarySeq;
	/* 유저 고유 번호 */
	private int pkUserSeq;
	/* 단어장 키워드 분류 */
	private String classId;
	/*용어명*/
	private String word;
	/*용어 뜻*/
	private String content;
	/*등록 아이디*/
	private String registId;
	/*등록 일자*/
	private String registDt;
	/*수정 아이디*/
	private String updateId;
	/*수정 일자*/
	private String updateDt;
	
	
}