package project.momento.subject.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.education.dto.EducationDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*과목*/
public class SubjectDto extends EducationDto{
	
	/*과목 고유 식별 번호*/
	private int pkSubjectSeq;
	/*교육 고유 식별 번호*/
	private int pkEducationSeq;
	/*관리자 고유 식별 번호*/
	private int pkManagerSeq;
	/*과목 이름*/
	private String subjectName;
	/*과목 상태(A: 준비, B: 진행, C: 종료)*/
	private String subjectState;
	/*강의 시작일자*/
	private String subStartDt;
	/*강의 종료일자*/
	private String subEndDt;
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