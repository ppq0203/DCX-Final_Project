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
	/*교육 이름*/  
	private String educationName;
	/*교육 상태(A: 준비, B: 진행, C: 종료)*/  
	private String educationState;
	/*강의 시작일자*/  
	private String educationDt;
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
	/*과목 명*/  
	private String subjectName;

}