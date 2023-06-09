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
	/*등록일자*/
	private String registDt;

}