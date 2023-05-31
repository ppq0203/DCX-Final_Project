package project.momento.bookmark.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*즐겨찾기*/
public class BookmarkDto {
	
	/*즐겨찾기 고유 번호*/
	private int pkUserBookmarkSeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkUserSeq;
	/*문제에 부여되는 고유 번호*/
	private int pkQuestionSeq;
	/*줄겨찾기 추가 날짜*/
	private String bookmarkDt;
	/*즐겨찾기 삭제 날짜*/
	private String bookmarkDtOut;

}