package project.momento.notice.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*공지사항 관리*/
public class NoticeDto {
	
	/*유저 개인에게 부여되는 고유번호*/
	private int pkNoticeSeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkEducationSeq;
	/*공지사항 제목*/
	private String title;
	/*공지사항 내용*/
	private String contents;
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

}