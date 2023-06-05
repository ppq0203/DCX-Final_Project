package project.momento.question.dto;

import org.springframework.context.annotation.PropertySource;
import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class TestcaseDto2 {
	
	/*문제에 부여되는 고유번호*/ 
	private int pkQuestionSeq;
	/*테스트 케이스 입력*/   
	private String input;
	/*테스트 케이스 출력*/   
	private String output;
	
}