package project.momento.socket.controller;

import lombok.RequiredArgsConstructor;
import project.momento.chat.dto.ChatDto;
import project.momento.question.service.TestcaseService;
import project.momento.room.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	private RoomService shambles;
	
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		message.setChatContent(message.getPkUserSeq() + "님이 채팅방에 참여하였습니다.");
		
		
		String userUUID = shambles.addUser(message);
		shambles.getUserList(message);
		
		headerAccessor.getSessionAttributes().put("userUUID",userUUID);
        headerAccessor.getSessionAttributes().put("roomId",message.getPkRoomSeq());
        
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
	
	//유저 퇴장 시에는 EventListener 를 통해서 유저 퇴장을 확인
    @EventListener
    public void webSocketDisconnectListener(SessionDisconnectEvent event){

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println(" [+] " + headerAccessor);

        // stomp 세션에 있던 uuid 와 roomId 를 확인하여 채팅방 유저 리스트와 room에서 해당 유저를 삭제
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        System.out.println(" [+] " + userUUID);
        System.out.println(" [+] " + roomId);

        // 채팅방 유저 -1
//        shambles.decreaseUser(roomId);

        //채팅방 유저 리스트에서 UUID 유저 닉네임 조회 및 리스트에서 유저 삭제
        String userName = shambles.getUserName(roomId, userUUID);
        shambles.delUser(roomId,userUUID);

        if(userName != null){
        	
        	String test = userUUID + "님이 채팅방에 참여하였습니다.";
            template.convertAndSend("/sub/chat/room/" + roomId, test);
        }
    }
	
}
