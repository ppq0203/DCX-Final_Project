package project.momento.question.dto;

import java.util.List;

import org.springframework.context.annotation.PropertySource;
import lombok.Data;
import project.momento.login.dto.LoginDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class QuestionDto extends LoginDto {
	
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
	/*입력값 예시*/
	private String input;
	/*출력값 예시*/
	private String output;
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

	private String[] levels;
	
	private int probNum;
	
	
	/*AI 문제 요약*/   
	private String summary;
	/*AI 문제 주제*/   
	private String title;
	/*AI 문제 원문*/   
	private String passage;
	/*AI 문제 URL*/   
	private String url;
	/*AI 문제 답변*/   
	private String answer;
	/*AI 문제 갯수*/   
	private int questionNum;
	
	private int answerNum;
	
	private List<String> solutions;
	
	private List<String> answers;
	
}
