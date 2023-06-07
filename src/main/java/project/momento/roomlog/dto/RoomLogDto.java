package project.momento.roomlog.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*방*/
public class RoomLogDto {
	
	/*방 로그 고유번호*/
	private int pkRoomLogSeq;
	/*방에 부여되는 고유번호*/
	private int pkRoomSeq;
	/*게임 관전 유저*/
	private int Observer;


}