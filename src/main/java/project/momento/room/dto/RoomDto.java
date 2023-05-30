package project.momento.room.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*방*/
public class RoomDto {
	
	/*방에 부여되는 고유번호*/
	private int pkRoomSeq;
	/*유저가 게임할 수 있는 방*/
	private String roomName;
	/*방 입장하는 비밀번호*/
	private int roomPwd;
	/*유저가 게임 종류 선택*/
	private String roomType;
	/*게임 플레이 횟수*/
	private int roomPlay;
	/*방 시간 설정*/
	private String roomPlaytime;
	/*방 설정 인원*/
	private int roomNumber;
	/*방장 아이디*/
	private String roomId;
	/*방 생성 일자*/
	private String roomTime;
	/*방 수정 시간*/
	private String roomUpdate;

}