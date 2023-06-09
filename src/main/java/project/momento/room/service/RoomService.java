package project.momento.room.service;

import project.momento.chat.dto.ChatDto;
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
	
	public Map<String, RoomDto> roomDtoMap; // 임시적으로 구현한 채팅방 저장을 위한 Map
	
	@PostConstruct
	private void init() {
		roomDtoMap = new LinkedHashMap<>();
	} // init()를 통한 Map 초기화
	
	public List<RoomDto> findAllRooms(String roomType) {
		List<RoomDto> rooms = new ArrayList<>(roomDtoMap.values());
		List<RoomDto> result = new ArrayList<>();
		for(RoomDto room : rooms)
		{
			if (roomType == null)
			{
				result.add(room);
			}
			else if (roomType.equals(room.getRoomType()))
			{
				result.add(room);				
			}
		}
//		Collections.reverse(result);
		
		return result;
	} // 전체 방 찾기
	
	public RoomDto findRoomById(String pkRoomSeq) {
		for (String key : roomDtoMap.keySet()) {
			System.out.println(key);
		}
		return roomDtoMap.get(pkRoomSeq);
	} // ID로 방 찾기 (검색기능)
	
	public RoomDto createRoomDto(RoomDto roomDto) {
		roomDto.setPkRoomSeq(UUID.randomUUID().toString());
		if(roomDto.getTeamNumber() != 0)
			roomDto.setTotal(roomDto.getRoomNumber()*roomDto.getTeamNumber());
		else
			roomDto.setTotal(roomDto.getRoomNumber());
		roomDto.setParticipants(0);
//		RoomDto room = RoomDto.create(roomDto);
		roomDtoMap.put(roomDto.getPkRoomSeq(), roomDto);
		
		return roomDto;
	} // 방 생성

	public String addUser(ChatDto message) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(message.getPkRoomSeq());
		String userUUID = UUID.randomUUID().toString();
		room.getUserList().put(userUUID, message.getPkUserSeq());
		room.setParticipants(room.getParticipants() + 1);
		return userUUID;
	}
	
	public void delUser(String roomId, String userUUID) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(roomId);
		room.getUserList().remove(userUUID);
		room.getTeam1().remove(userUUID);
		room.getTeam2().remove(userUUID);
		room.getTeam3().remove(userUUID);
		room.setParticipants(room.getParticipants() - 1);
	}

	public HashMap<String, String> getUserList(ChatDto message) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(message.getPkRoomSeq());
		HashMap<String, String> userList = room.getUserList();
		System.out.println(room.getUserList());
		return userList;
	}

	public String getUserName(String roomId, String userUUID) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(roomId);
		String userName = null;
		if(room.getUserList().get(userUUID) != null){
			userName = room.getUserList().get(userUUID);
		}
		else if(room.getTeam1().get(userUUID) != null) {
			userName = room.getTeam1().get(userUUID);
		}
		else if(room.getTeam2().get(userUUID) != null){
			userName = room.getTeam2().get(userUUID);
		}
		else {
			userName = room.getTeam3().get(userUUID);
		}

        return userName;
	}
}