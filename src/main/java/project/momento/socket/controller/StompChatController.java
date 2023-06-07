package project.momento.socket.controller;

import lombok.RequiredArgsConstructor;
import project.momento.chat.dto.ChatDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	private final SimpMessagingTemplate template;
	
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatDto message) {
		message.setChatContent(message.getPkUserSeq() + "님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getPkRoomSeq(), message);
	}
	
	@MessageMapping(value= "/chat/message")
	public void message(ChatDto message) {
		template.convertAndSend("/sub/chat/room/" + message.getPkRoomSeq(), message);
	}
}
