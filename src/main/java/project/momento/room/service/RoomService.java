package project.momento.room.service;

import project.momento.room.dto.RoomDto;
import project.momento.room.mapper.RoomMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct; // 임시로 채용한 어
import java.util.*;

@Service
public class RoomService {
	
	/*
	 * 해당 함수가 원래 처리할려고 했던 함수. 근데 완성된건 아님. 그냥 잠깐 구현해놓다가 만거여서
	 * 제대로 작동 안됨.
	 * @Autowired private RoomMapper mapper;
	 * 
	 * public List<RoomDto> findAllRooms() { return mapper.findAllRooms(); }
	 * 
	 * public List<RoomDto> findRoomByName(String roomName) { return
	 * mapper.findRoomByName(roomName); }
	 * 
	 * public List<RoomDto> createRoomDTO(String roomName) { return
	 * mapper.createRoom(roomName); }
	 */
	
	private Map<String, RoomDto> roomDtoMap; // 임시적으로 구현한 채팅방 저장을 위한 Map
	
	@PostConstruct
	private void init() {
		roomDtoMap = new LinkedHashMap<>();
	} // init()를 통한 Map 초기화
	
	public List<RoomDto> findAllRooms() {
		List<RoomDto> result = new ArrayList<>(roomDtoMap.values());
		Collections.reverse(result);
		
		return result;
	} // 전체 방 찾기
	
	public RoomDto findRoomById(String id) {
		return roomDtoMap.get(id);
	} // ID로 방 찾기 (검색기능)
	
	public RoomDto createRoomDto(String name) {
		RoomDto room = RoomDto.create(name);
		roomDtoMap.put(room.getPkRoomSeq(), room);
		
		return room;
	} // 방 생성
}
