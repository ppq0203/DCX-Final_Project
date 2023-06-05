package project.momento.question.dto;

import org.springframework.context.annotation.PropertySource;
import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class TestcaseDto {
	
	/*문제에 부여되는 고유번호*/ 
	private int pkQuestionSeq;
	/*입력*/ 
	private String input;
	/*출력*/ 
	private String output;
	/*등록 아이디*/   
	private String registId;   
	/*등록 일자*/   
	private String registDt;   
	/*수정 아이디*/   
	private String updateId;   
	/*수정 일자*/   
	private String updateDt;
	
}
