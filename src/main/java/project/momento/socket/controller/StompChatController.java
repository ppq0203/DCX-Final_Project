package project.momento.socket.controller;

import lombok.RequiredArgsConstructor;
import project.momento.answer.dto.AnswerDto;
import project.momento.answer.service.AnswerService;
import project.momento.chat.dto.ChatDto;
import project.momento.login.dto.LoginDto;
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
import java.util.Set;

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

import jakarta.servlet.http.HttpServletRequest;

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
		HashMap<String, HashMap> userList = room.getUserList();
		
		headerAccessor.getSessionAttributes().put("userUUID",userUUID);
        headerAccessor.getSessionAttributes().put("roomId",message.getPkRoomSeq());
        headerAccessor.getSessionAttributes().put("userTeamNumber", "waitList");
        
        message.setPkUserSeq(userUUID);
		template.convertAndSend("/sub/chat/system/" + message.getPkRoomSeq(), message);
		template.convertAndSend("/sub/chat/userList/" + message.getPkRoomSeq(), userList);
	}
	
	@MessageMapping(value="/chat/system")
	public void systemNoti(@Payload ChatDto message) {
		message.setChatContent("방방봐 테스트");
		message.setPkUserSeq("SYSTEM");
		template.convertAndSend("/sub/chat/system/" + message.getPkRoomSeq(), message);
	}
	
	@MessageMapping(value="/chat/TeamChange")
	public void changeTeam(@Payload ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		RoomDto room = shambles.roomDtoMap.get(message.getPkRoomSeq());

		String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        String team = (String) headerAccessor.getSessionAttributes().get("userTeamNumber");
        
        if(!message.getUserTeamNumber().equals(team))
        {
	        if(room.getUserList().get(message.getUserTeamNumber()) == null)
			{
				HashMap dump = new HashMap();
				dump.put("dump", "dump");
				room.getUserList().put(message.getUserTeamNumber(), dump);
				room.getUserList().get(message.getUserTeamNumber()).put(userUUID, room.getUserList().get(team).get(userUUID));
				room.getUserList().get(message.getUserTeamNumber()).remove("dump");
			}
	        else
	        {
	        	room.getUserList().get(message.getUserTeamNumber()).put(userUUID, room.getUserList().get(team).get(userUUID));
	        }
        
        	room.getUserList().get(team).remove(userUUID);
        }
        
        HashMap<String, HashMap> userList = room.getUserList();
        headerAccessor.getSessionAttributes().put("userTeamNumber", message.getUserTeamNumber());
		template.convertAndSend("/sub/chat/userList/" + message.getPkRoomSeq(), userList);
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
        String team = (String) headerAccessor.getSessionAttributes().get("userTeamNumber");
        
        // 채팅방 유저 -1
//        shambles.decreaseUser(roomId);

        //채팅방 유저 리스트에서 UUID 유저 닉네임 조회 및 리스트에서 유저 삭제
        String userName = shambles.getUserName(roomId, userUUID, team);

        if(userName != null){
        	ChatDto test = new ChatDto();
        	test.setChatContent(userName + "님이 채팅방에 퇴장하셨습니다.");
            template.convertAndSend("/sub/chat/system/" + roomId, test);
            template.convertAndSend("/sub/chat/rm/" + roomId, userUUID);
        }
        
        shambles.delUser(roomId,userUUID, team);
        
        RoomDto room = shambles.roomDtoMap.get(roomId);
        if((room.getUserList().get("waitList") == null || room.getUserList().get("waitList").isEmpty()) 
        		&& (room.getUserList().get("team1") == null || room.getUserList().get("team1").isEmpty()) 
        		&& (room.getUserList().get("team2") == null || room.getUserList().get("team2").isEmpty())
        		&& (room.getUserList().get("team3") == null || room.getUserList().get("team3").isEmpty()) 
        		&& (room.getUserList().get("team4") == null || room.getUserList().get("team4").isEmpty()))
        {
        	shambles.roomDtoMap.remove(roomId);
        }
    }
	
    @MessageMapping(value= "/chat/gamestart")
	public void gameStart(MultigameResultDto message, SimpMessageHeaderAccessor headerAccessor) {
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String userTeamNumber = (String) headerAccessor.getSessionAttributes().get("userTeamNumber");
        RoomDto roomDto = shambles.roomDtoMap.get(roomId);
        roomDto.setIsRunning(1);
    	message.setTeamNo(userTeamNumber.replace("team", ""));
    	message.setUserNo(userUUID);
		template.convertAndSend("/sub/chat/gamestart/" + roomId, message);
	}
    
    @MessageMapping(value= "/chat/correct")
	public void correctUser(MultigameResultDto message, SimpMessageHeaderAccessor headerAccessor) {
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String userTeamNumber = (String) headerAccessor.getSessionAttributes().get("userTeamNumber");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
    	message.setTeamNo(userTeamNumber.replace("team", ""));
    	String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(message);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(json);
		template.convertAndSend("/sub/chat/correct/" + message.getRoomId(), json);
        RoomDto roomDto = shambles.roomDtoMap.get(roomId);
        Set<String> giveupList = roomDto.getGiveupList();
		giveupList.clear();
	}
    @MessageMapping(value= "/chat/giveup")
	public void giveup(MultigameResultDto message, SimpMessageHeaderAccessor headerAccessor) {
        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
        RoomDto roomDto = shambles.roomDtoMap.get(roomId);
        // 포기한사람을 저장
        Set<String> giveupList = roomDto.getGiveupList();
        giveupList.add(userUUID);
        System.out.println(giveupList.size());
    	// 전부 포기 했을때
    	if(giveupList.size() >= roomDto.getParticipants())
        {
    		message.setTeamNo("0");
        	message.setUserNo("0");
        	String json = null;
    		template.convertAndSend("/sub/chat/correct/" + roomId, message);
    		giveupList.clear();
    	}
	}
    
    @MessageMapping(value="/chat/sendAnswer")
	public void sendAnswer(AnswerDto answerDto, SimpMessageHeaderAccessor headerAccessor) {

        String userUUID = (String) headerAccessor.getSessionAttributes().get("userUUID");
        String roomId = (String) headerAccessor.getSessionAttributes().get("roomId");
		String code = answerDto.getAnswerUser();
		String name = answerDto.getAnswerOx();
		int pkUserSeq = answerDto.getPkUserSeq();
		int num = answerDto.getPkQuestionSeq();
		
		
		List<TestcaseDto> testcaseDtos = testcaseService.selectTestcaseList(num);
		
													// 방넘버, 유저넘저, 함수명, 인풋list, 함수실행코드
		int result = StringCodeCompile.stringCodeCompile(roomId, userUUID, name, testcaseDtos, code);
		// 문제번호, 유저번호, 코드, 결과, 문제타입
		AnswerToDB.answerToDB(num, pkUserSeq, code, result, questionService.selectQuestionSeq(num).getType(), answerService);
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
