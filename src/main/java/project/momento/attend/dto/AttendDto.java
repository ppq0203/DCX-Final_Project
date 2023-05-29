package project.momento.attend.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*출결관리*/
public class AttendDto {
	
	/*출석 식별 번호*/
	private int pkAttendSeq;
	/*사용자 고유번호*/
	private int pkUserSeq;
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
	/*등록일자*/
	private String registDt;

}