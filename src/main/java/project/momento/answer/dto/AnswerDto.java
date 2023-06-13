package project.momento.answer.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.question.dto.QuestionDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*풀이 답*/
public class AnswerDto extends QuestionDto {

	/*답변에 부여되는 고유번호*/
	private int pkAnswerSeq;
	/*제출답안에 부여되는 고유 번호*/
	private int pkQuestionSeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkUserSeq;
	/*제출 답안*/
	private String answerUser;
	/*답안 정답 확인*/
	private String answerOx;
	/*답안 제출 일자*/
	private String registDt;
	/*문제 타입*/
	private String type;
	
	private String solveTime;
	// 임시변수 유저고유번호 int로 받을수있게되면 삭제
	private String userUUID;
}