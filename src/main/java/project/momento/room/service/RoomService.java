package project.momento.room.service;

import project.momento.room.dto.RoomDto;
import project.momento.room.mapper.RoomMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RoomService {
	
	@Autowired
	private RoomMapper mapper;
	
	public List<RoomDto> findAllRooms() {
		return mapper.findAllRooms();
	}
	
	public List<RoomDto> findRoomByName(String roomName) {
		return mapper.findRoomByName(roomName);
	}
	
	public List<RoomDto> createRoomDTO(String roomName) {
		return mapper.createRoom(roomName);
	}
}
