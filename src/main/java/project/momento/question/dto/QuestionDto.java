package project.momento.question.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class QuestionDto {
	
	/*문제에 부여되는 고유번호*/ 
	private int pkQuestionSeq;
	/*문제의 난이도*/   
	private String questionLevel;
	/*문제의 점수*/
	private String questionScore;
	/*문제의 유형*/  
	private String pkQuestionType;  
	/*문제본문*/
	private String field3;  
	/*문제의 풀이*/   
	private String questionExplanation;   
	/*힌트*/   
	private String quetionChange;   
	/*문제의 등록일*/   
	private String registDt; 

}