package project.momento.socket.controller;

import lombok.RequiredArgsConstructor;
import project.momento.chat.dto.ChatDto;
import project.momento.question.service.TestcaseService;
import project.momento.room.dto.RoomDto;
import project.momento.room.service.RoomService;
import project.momento.socket.dto.MultigameResultDto;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	private RoomService shambles;
	
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		RoomDto room = shambles.roomDtoMap.get(message.getPkRoomSeq());
		
		message.setChatContent(message.getPkUserSeq() + "님이 채팅방에 참여하였습니다.");
		
		String userUUID = shambles.addUser(message);
		HashMap<String, String> userList = room.getUserList();
		HashMap<String, String> team1List = room.getTeam1();
		HashMap<String, String> team2List = room.getTeam2();
		HashMap<String, String> team3List = room.getTeam3();
		HashMap<String, String> team4List = room.getTeam4();
		
		headerAccessor.getSessionAttributes().put("userUUID",userUUID);
        headerAccessor.getSessionAttributes().put("roomId",message.getPkRoomSeq());
        
        message.setPkUserSeq(userUUID);
        
		template.convertAndSend("/sub/chat/system/" + message.getPkRoomSeq(), message);
		template.convertAndSend("/sub/chat/team1List/" + message.getPkRoomSeq(), team1List);
		template.convertAndSend("/sub/chat/team2List/" + message.getPkRoomSeq(), team2List);
		template.convertAndSend("/sub/chat/team3List/" + message.getPkRoomSeq(), team3List);
		template.convertAndSend("/sub/chat/team4List/" + message.getPkRoomSeq(), team4List);
	}
	
	@MessageMapping(value="/chat/system")
	public void systemNoti(@Payload ChatDto message) {
		message.setChatContent("방방봐 테스트");
		message.setPkUserSeq("SYSTEM");
		template.convertAndSend("/sub/chat/system/" + message.getPkRoomSeq(), message);
	}
	
	@MessageMapping(value="/chat/Team1Change")
	public void changeTeam(@Payload ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		RoomDto room = shambles.roomDtoMap.get(message.getPkRoomSeq());
		
		String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        
        if(room.getUserList().get(userUUID) != null)
        {
        	room.getTeam1().put(userUUID, room.getUserList().get(userUUID));
        	room.getUserList().remove(userUUID);
        }
        else if(room.getTeam2().get(userUUID) != null){
        	room.getTeam1().put(userUUID, room.getTeam2().get(userUUID));
        	room.getTeam2().remove(userUUID);
        }
        if(room.getTeam3().get(userUUID) != null)
        {
        	room.getTeam1().put(userUUID, room.getTeam3().get(userUUID));
        	room.getTeam3().remove(userUUID);
        }
        else if(room.getTeam4().get(userUUID) != null)
        {
        	room.getTeam1().put(userUUID, room.getTeam4().get(userUUID));
        	room.getTeam4().remove(userUUID);
        }
        
        HashMap<String, String> userList = room.getTeam1();
        
		template.convertAndSend("/sub/chat/team1List/" + message.getPkRoomSeq(), userList);
	}
	
	@MessageMapping(value="/chat/Team2Change")
	public void change1Team(@Payload ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		RoomDto room = shambles.roomDtoMap.get(message.getPkRoomSeq());
		
		String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        
        if(room.getUserList().get(userUUID) != null)
        {
        	room.getTeam2().put(userUUID, room.getUserList().get(userUUID));
        	room.getUserList().remove(userUUID);
        }
        else if(room.getTeam1().get(userUUID) != null){
        	room.getTeam2().put(userUUID, room.getTeam1().get(userUUID));
        	room.getTeam1().remove(userUUID);
        }
        if(room.getTeam3().get(userUUID) != null)
        {
        	room.getTeam2().put(userUUID, room.getTeam3().get(userUUID));
        	room.getTeam3().remove(userUUID);
        }
        else if(room.getTeam4().get(userUUID) != null)
        {
        	room.getTeam2().put(userUUID, room.getTeam4().get(userUUID));
        	room.getTeam4().remove(userUUID);
        }
        
        HashMap<String, String> userList = room.getTeam2();
                
		template.convertAndSend("/sub/chat/team2List/" + message.getPkRoomSeq(), userList);
	}
	
	@MessageMapping(value="/chat/Team3Change")
	public void change2Team(@Payload ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		RoomDto room = shambles.roomDtoMap.get(message.getPkRoomSeq());
		
		String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        
        if(room.getUserList().get(userUUID) != null)
        {
        	room.getTeam3().put(userUUID, room.getUserList().get(userUUID));
        	room.getUserList().remove(userUUID);
        }
        else if(room.getTeam2().get(userUUID) != null){
        	room.getTeam3().put(userUUID, room.getTeam2().get(userUUID));
        	room.getTeam2().remove(userUUID);
        }
        if(room.getTeam1().get(userUUID) != null)
        {
        	room.getTeam3().put(userUUID, room.getTeam1().get(userUUID));
        	room.getTeam1().remove(userUUID);
        }
        else if(room.getTeam4().get(userUUID) != null)
        {
        	room.getTeam3().put(userUUID, room.getTeam4().get(userUUID));
        	room.getTeam4().remove(userUUID);
        }
        
        HashMap<String, String> userList = room.getTeam3();
        
		template.convertAndSend("/sub/chat/team3List/" + message.getPkRoomSeq(), userList);
	}
	
	@MessageMapping(value="/chat/Team4Change")
	public void change3Team(@Payload ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		RoomDto room = shambles.roomDtoMap.get(message.getPkRoomSeq());
		
		String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        
        if(room.getUserList().get(userUUID) != null)
        {
        	room.getTeam4().put(userUUID, room.getUserList().get(userUUID));
        	room.getUserList().remove(userUUID);
        }
        else if(room.getTeam2().get(userUUID) != null){
        	room.getTeam4().put(userUUID, room.getTeam2().get(userUUID));
        	room.getTeam2().remove(userUUID);
        }
        if(room.getTeam3().get(userUUID) != null)
        {
        	room.getTeam4().put(userUUID, room.getTeam3().get(userUUID));
        	room.getTeam3().remove(userUUID);
        }
        else if(room.getTeam1().get(userUUID) != null)
        {
        	room.getTeam4().put(userUUID, room.getTeam1().get(userUUID));
        	room.getTeam1().remove(userUUID);
        }
        
        HashMap<String, String> userList = room.getTeam4();
        
		template.convertAndSend("/sub/chat/team4List/" + message.getPkRoomSeq(), userList);
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
        
        // 채팅방 유저 -1
//        shambles.decreaseUser(roomId);

        //채팅방 유저 리스트에서 UUID 유저 닉네임 조회 및 리스트에서 유저 삭제
        String userName = shambles.getUserName(roomId, userUUID);

        if(userName != null){
        	ChatDto test = new ChatDto();
        	test.setChatContent(userName + "님이 채팅방에 퇴장하셨습니다.");
            template.convertAndSend("/sub/chat/system/" + roomId, test);
            template.convertAndSend("/sub/chat/rm/" + roomId, userUUID);
        }
        
        shambles.delUser(roomId,userUUID);
    }
	
    @MessageMapping(value= "/chat/gamestart")
	public void gameStart(ChatDto message) {
    	
		template.convertAndSend("/sub/chat/gamestart/" + message.getPkRoomSeq(), message);
	}
    
    @MessageMapping(value= "/chat/correct")
	public void correctUser(MultigameResultDto message, SimpMessageHeaderAccessor headerAccessor) {
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
    	message.setTeamNo("4");
    	message.setType("Array");
    	message.setUserNo(userUUID);
    	String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		template.convertAndSend("/sub/chat/correct/" + message.getRoomId(), json);
	}
    
}
