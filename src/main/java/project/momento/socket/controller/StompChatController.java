package project.momento.socket.controller;

import lombok.RequiredArgsConstructor;
import project.momento.chat.dto.ChatDto;
import project.momento.question.service.TestcaseService;
import project.momento.room.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	private RoomService shambles;
	
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatDto message) {
		message.setChatContent(message.getPkUserSeq() + "님이 채팅방에 참여하였습니다.");
		shambles.addUser(message);
		shambles.getUserList(message);
		template.convertAndSend("/sub/chat/room/" + message.getPkRoomSeq(), message);
	}
	
	@MessageMapping(value="/chat/system")
	public void systemNoti(@Payload ChatDto message) {
		message.setChatContent("방방봐 테스트");
		template.convertAndSend("/sub/chat/system/" + message.getPkRoomSeq(), message);
	}
	
	@MessageMapping(value= "/chat/message")
	public void message(ChatDto message) {
		template.convertAndSend("/sub/chat/room/" + message.getPkRoomSeq(), message);
	}
}
