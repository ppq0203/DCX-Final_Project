package project.momento.room.mapper;

import project.momento.room.dto.RoomDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoomMapper {
	List<RoomDto> findAllRooms();
	List<RoomDto> createRoom(String roomName);
	List<RoomDto> findRoomByName(String roomName);
}
