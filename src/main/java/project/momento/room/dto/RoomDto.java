package project.momento.room.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.annotation.PropertySource;
import lombok.Data;

import org.springframework.web.socket.WebSocketSession;


@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*방*/
public class RoomDto {
	
	private HashMap<String,String> userList = new HashMap<>(); /*참여중인 유저의 리스트를 뽑기 위한 리스트*/
	private HashMap<String,String> team1 = new HashMap<>(); /*팀1에 참여중인 유저의 리스트를 뽑기 위한 리스트*/
	private HashMap<String,String> team2 = new HashMap<>(); /*팀2에 참여중인 유저의 리스트를 뽑기 위한 리스트*/
	private HashMap<String,String> team3 = new HashMap<>(); /*팀3에 참여중인 유저의 리스트를 뽑기 위한 리스트*/
	
	private String pkRoomSeq; /*방에 부여되는 고유번호 roomId (지금 임시적으로 세션만으로 구현하는 형태로 String형으로 하였음. DB사용할때는 int로 처리할것. */ 
	
	private String roomName; /*유저가 게임할 수 있는 방 이름  name */
	
	private int roomPwd; /*방 입장하는 비밀번호*/
	
	private String roomType; /*팀전/개인전 선택*/
	
	private int roomPlay; /*게임 플레이 횟수*/
	
	private String roomPlaytime; /*방 시간 설정*/
	
	private int roomNumber; /*팀당 개수*/
	
	private int teamNumber; /*팀당 인원*/ //db에 col추가해야함
	
	private int roomLevel; /*방 난이도*/ //db에 col추가해야함
	
	private String roomId; /*방장 아이디*/
	
	private String roomTime; /*방 생성 일자*/
	
	private String roomUpdate; /*방 수정 시간*/
	
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	public static RoomDto create(String name) {
		RoomDto room = new RoomDto();
		
		room.pkRoomSeq = UUID.randomUUID().toString();
		room.roomName = name;
		return room;
	} // 해당 create 함수는 db가 없는 대신 방 생성 시 임시적으로 primary key값(pkRoomSeq)을 만들기 위해 UUID패키지를 통해 랜덤값 생성함.
	// DB 연동 시 해당 함수는 필요없게 됨.

}