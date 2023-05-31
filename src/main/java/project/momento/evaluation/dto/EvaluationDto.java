package project.momento.evaluation.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*강의 평가 관리*/
public class EvaluationDto {
		
	/*강의 평가 고유 식별 번호*/
	private int pkEvaluateSeq;
	/*강의 고유 식별 번호*/
	private int pkSubjectSeq;
	/*사용자 고유 식별 번호*/
	private int pkUserSeq;
	/*학생용 강의 평가*/
	private String contents;
	/*등록 아이디*/
	private String registId;
	/*등록 일자*/
	private String registDt;
	/*수정 아이디*/
	private String updateId;
	/*수정 일자*/
	private String updateDt;

}