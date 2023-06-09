package project.momento.answer.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*풀이 답*/
public class AnswerDto {
	
	/*제출답안에 부여되는 고유 번호*/
	private int pkAnswerSeq;
	/*방에 부여되는 고유번호*/
	private int pkQuestionSeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkUserSeq;
	/*제출 답안*/
	private String answerUser;
	/*답안 정답 확인*/
	private String answerOx;
	/*답안 제출 일자*/
	private String registDt;
	
}