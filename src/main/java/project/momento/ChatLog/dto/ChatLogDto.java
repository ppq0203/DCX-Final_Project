package project.momento.ChatLog.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*방*/
public class ChatLogDto {
	
	/*채팅 내용 식별 번호*/
	private int pkChatSeq;
	/*유저 개인에게 부여되는 고유번호*/
	private int pkUserSeq;
	/*방에 부여되는 고유번호*/
	private int pkRoomSeq;
	/*유저 채팅 시간 기록 (로비)*/
	private String chatStamp;
	/*유저 채팅 로그 내용 기록 (로비)*/
	private String chatContent;
	/*유저 채팅 시간 기록 (방)*/
	private String chatStampRoom;
	/*유저 채팅 로그 내용 기록 (방)*/
	private String chartContentRoom;
	
}