package project.momento.chat.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ChatDto {
	
	
	private int pkChatSeq; // 채팅 내용 식별 번호
	
	private String pkUserSeq; // 유저 개인에게 부여되는 고유번호 writer / 임시적으로 String형으로 변경
	
	private String pkRoomSeq; // 방에 부여되는 고유번호 roomId / 임시적으로 String형으로 변경
	
	//private String chatStamp; // 유저 채팅 시간 기록 (로비)
	
	private String chatContent; // 유저 채팅 로그 내용 기록 (로비) message
	
	//private String chatStampRoom; // 유저 채팅 시간 기록 (방)
	
	//private String chatContentRoom; // 유저 채팅 로그 내용 기록 (방)
}
