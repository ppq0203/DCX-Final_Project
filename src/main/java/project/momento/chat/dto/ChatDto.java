package project.momento.chat.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ChatDto {
	private int pkChatSeq;
	
	private String pkUserSeq;
	
	private String pkRoomSeq;
	
	private String chatStamp;
	
	private String chatContent;
	
	private String chatStampRoom;
	
	private String chatContentRoom;
}
