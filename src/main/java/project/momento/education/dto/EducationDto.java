package project.momento.education.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.login.dto.LoginDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*강의관리*/
public class EducationDto extends LoginDto {
	
	/*과목 고유 식별 번호*/
	private int pkEducationSeq;
	/*과목 고유 식별 번호*/
	private int pkSubjectSeq;
	/*교육 이름*/  
	private String educationName;
	/*교육 상태(A: 준비, B: 진행, C: 종료)*/  
	private String educationState;
	/*교육 일자*/  
	private String educationDt;
	/*과목 일자*/
	private String subjectDt;
	/*강의 시작일자*/  
	private String subStartDt;
	/*강의 종료일자*/  
	private String subEndDt;
	/*담당 선생*/  
	private String educationTeather;
	/*과목의 시험*/  
	private String educationTest;
	/*시험 날짜*/  
	private String educationTestDt;
	/*등록 아이디*/  
	private String registId;
	/*등록 일자*/  
	private String registDt;
	/*수정 아이디*/  
	private String updateId;
	/*수정 일자*/  
	private String updateDt;
	/*관리자 고유 식별 번호*/
	private int pkManagerSeq;
	/*학생 고유 식별 번호*/
	private int pkUserSeq;
	/*학생 고유 식별 번호*/
	private String pkUserSeqArray;
	/*학생 고유 식별 번호*/
	private String pkManagerSeqArray;
	/*과목 명*/  
	private String subjectName;
	/*반환 SEQ*/
	private int returnId;
	/*과목 상태*/
	private String subjectState;
	/*출석 구분*/
	private String attendDivn;
	/*출석 일자*/
	private String attendDt;
	/*조퇴 구분*/
	private String earlyDivn;
	/*조퇴 일자*/
	private String earlyDt;
	/*지각 구분*/
	private String lateDivn;
	/*지각 일자*/
	private String lateDt;
	/*휴가 구분*/
	private String relaxDivn;
	/*휴가 일자*/
	private String relaxDt;
	/*결석 구분*/
	private String absentDivn;
	/*결석한 학생 날짜*/
	private String absentDt;
	/*외출 구분*/
	private String outDivn;
	/*외출 일자*/
	private String outDt;
	/*복귀 일자*/
	private String inDt;

}