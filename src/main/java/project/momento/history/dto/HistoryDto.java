package project.momento.history.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*기록*/
public class HistoryDto {
	
	/*유저 획득 점수*/
	private int pkUserHistorySeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkUserSeq;
	/*문제에 부여되는 고유 번호*/
	private int pkQuestionSeq;
	/*유저 모든 점수 합산*/
	private int allScore;
	/*문제 완료한 유형*/
	private String questionType;
	/*문제 수행 완료 날짜*/
	private String questionDt;

}