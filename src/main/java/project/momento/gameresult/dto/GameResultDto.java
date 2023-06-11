package project.momento.gameresult.dto;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*결과*/
public class GameResultDto {

	/*결과에 부여되는 고유번호*/
	private int pkQuestionSeq;
	/*내 팀의 번호*/
	private int myTeamNum;
	/*문제를 푼 팀의 번호*/
	private String solveTeamNum;
	/*내가 문제를 풀었는지 여부*/
	private int myResult;
	/*문제 유형*/
	private String questionType;

}
