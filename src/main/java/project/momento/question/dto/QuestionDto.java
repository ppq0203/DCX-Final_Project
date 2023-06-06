package project.momento.question.dto;

import org.springframework.context.annotation.PropertySource;
import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class QuestionDto {
	
	/*문제에 부여되는 고유번호*/ 
	private int pkQuestionSeq;
	/*문제명*/ 
	private String questionName;
	/*문제의 난이도*/   
	private String level;
	/*문제의 점수*/
	private int score;
	/*문제의 유형*/  
	private String type;  
	/*문제본문*/
	private String contents;  
	/*코드 형식*/
	private String codeFormat;  
	/*문제의 풀이*/   
	private String solution;   
	/*힌트*/   
	private String chance; 
	/*클래스명*/   
	private String className; 
	/*사용여부*/
	private String useYn;
	/*삭제여부*/
	private String delYn;
	/*등록 아이디*/
	private String registId;   
	/*등록 일자*/   
	private String registDt;   
	/*수정 아이디*/   
	private String updateId;   
	/*수정 일자*/   
	private String updateDt;
	
}
