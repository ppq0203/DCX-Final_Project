package project.momento.answer.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*방*/
public class AnwerDto {
	
	/*제출답안에 부여되는 고우 번호*/
	private int pkAnswerSeq;
	/*방에 부여되는 고유번호*/
	private int pkQuesTionSeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkUserSeq;
	/*유저의 정답*/
	private String userAnswer;
	/*답안 정답 확인*/
	private String AnswerOX;
	/*답안 제출 아이디*/
	private String registId;
	/*답안 제출 일자*/
	private String registDt;
	/*정답 수정 아이디*/
	private String updateId;
	/*정답 수정 일자*/
	private String uodateDt;
	
}