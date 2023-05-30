package project.momento.question.dto;

import org.springframework.context.annotation.PropertySource;
import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class QuestionDto {
	
	/*문제에 부여되는 고유번호*/ 
	private int pkQuestionSeq;
	/*문제의 난이도*/   
	private String questionName;
	
	private String level;
	/*문제의 점수*/
	private int score;
	/*문제의 유형*/  
	private String type;  
	/*문제본문*/
	private String contents;  
	/*문제의 풀이*/   
	private String solution;
	/*힌트*/
	private String chance;
	
	private String className;   
	/*등록 아이디*/   
	private String use_yn;
	
	private String del_yn;
	
	private String registId;   
	/*등록 일자*/   
	private String registDt;   
	/*수정 아이디*/   
	private String updateId;   
	/*수정 일자*/   
	private String updateDt;
	
}
