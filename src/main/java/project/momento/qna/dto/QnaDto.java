package project.momento.qna.dto;
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*Q&A*/
public class QnaDto {
	
	/*질문 고유 식별번호*/
	private int pkAskSeq;
	/*과목 고유 식별 번호*/
	private int pkSubjectSeq;
	/*질문 제목*/
	private String title;
	/*질문 내용*/
	private String contents;
	/*조회수*/
	private int count;
	/*등록 아이디*/
	private String registId;
	/*등록 일자*/
	private String registDt;
	/*수정 아이디*/
	private String updateId;
	/*수정 일자*/
	private String updateDt;
	
}