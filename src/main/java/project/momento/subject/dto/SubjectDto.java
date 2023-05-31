package project.momento.subject.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*강의관리*/
public class SubjectDto {
	
	/*과목 고유 식별 번호*/
	private int pkSubjectSeq;
	/*교육 이름*/  
	private String subjectName;
	/*교육 상태(A: 준비, B: 진행, C: 종료)*/  
	private String subjectState;
	/*강의 시작일자*/  
	private String subStartDt;
	/*강의 종료일자*/  
	private String subEndDt;
	/*담당 선생*/  
	private String subjectTeather;
	/*과목의 시험*/  
	private String subjectTest;
	/*시험 날짜*/  
	private String subjectTestDt;
	/*등록 아이디*/  
	private String registId;
	/*등록 일자*/  
	private String registDt;
	/*수정 아이디*/  
	private String updateId;
	/*수정 일자*/  
	private String updateDt;

}