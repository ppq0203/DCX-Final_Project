package project.momento.socket.controller;

import lombok.RequiredArgsConstructor;
import project.momento.answer.dto.AnswerDto;
import project.momento.answer.service.AnswerService;
import project.momento.chat.dto.ChatDto;
import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.AnswerToDB;
import project.momento.question.function.StringCodeCompile;
import project.momento.question.service.QuestionService;
import project.momento.question.service.TestcaseService;
import project.momento.room.dto.RoomDto;
import project.momento.room.service.RoomService;
import project.momento.socket.dto.MultigameResultDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	private RoomService shambles;
	
	@Autowired
	private TestcaseService testcaseService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
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
		template.convertAndSend("/sub/chat/waitList/" + message.getPkRoomSeq(), userList);
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
        headerAccessor.getSessionAttributes().put("userTeamNumber", "team1");
        
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
        headerAccessor.getSessionAttributes().put("userTeamNumber", "team2");
                
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
        headerAccessor.getSessionAttributes().put("userTeamNumber", "team3");
        
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
        headerAccessor.getSessionAttributes().put("userTeamNumber", "team4");
        
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
        
        RoomDto room = shambles.roomDtoMap.get(roomId);
        if(room.getUserList().isEmpty() && room.getTeam1().isEmpty() && room.getTeam2().isEmpty() 
        		&& room.getTeam3().isEmpty() && room.getTeam4().isEmpty()) 
        {
        	shambles.roomDtoMap.remove(roomId);
        }
    }
	
    @MessageMapping(value= "/chat/gamestart")
	public void gameStart(ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		template.convertAndSend("/sub/chat/gamestart/" + message.getPkRoomSeq(), message);
	}
    
    @MessageMapping(value= "/chat/correct")
	public void correctUser(MultigameResultDto message, SimpMessageHeaderAccessor headerAccessor) {
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String userTeamNumber = (String) headerAccessor.getSessionAttributes().get("userTeamNumber");
    	message.setTeamNo(userTeamNumber.replace("team", ""));
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
    @MessageMapping(value= "/chat/giveup")
	public void giveup(MultigameResultDto message, SimpMessageHeaderAccessor headerAccessor) {
        // 포기한사람을 저장
    	
    	// 전부 포기 했을때
    	if(false)
        {
    		message.setTeamNo("0");
        	message.setUserNo("0");
        	String json = null;
    		try {
    			json = new ObjectMapper().writeValueAsString(message);
    		} catch (JsonProcessingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		template.convertAndSend("/sub/chat/correct/" + message.getRoomId(), json);
        }
	}
    
    @MessageMapping(value="/chat/sendAnswer")
	public void sendAnswer(AnswerDto answerDto, SimpMessageHeaderAccessor headerAccessor) {

        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
		String code = answerDto.getAnswerUser();
		String name = answerDto.getAnswerOx();
		int num = answerDto.getPkQuestionSeq();
		
		System.out.println(" [+] " + code + " [+] " + name + " [+] " + num);
		
		List<TestcaseDto> testcaseDtos = testcaseService.selectTestcaseList(num);
		
													// 방넘버, 유저넘저, 함수명, 인풋list, 함수실행코드
		int result = StringCodeCompile.stringCodeCompile(roomId, userUUID, name, testcaseDtos, code);
		// 문제번호, 유저번호, 코드, 결과, 문제타입
		AnswerToDB.answerToDB(num, 1, code, result, questionService.selectQuestionSeq(num).getType(), answerService);
		if (result == 0)
			answerDto.setAnswerOx("O");
		else
			answerDto.setAnswerOx("X");
//		answerDto.setPkUserSeq(userUUID);
		answerDto.setUserUUID(userUUID);
		System.out.println("/sub/chat/sendAnswer/" + roomId + "/" + userUUID);
		template.convertAndSend("/sub/chat/sendAnswer/" + roomId, answerDto);
	}
}
