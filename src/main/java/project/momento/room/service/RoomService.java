package project.momento.room.service;

import project.momento.chat.dto.ChatDto;
import project.momento.login.dto.LoginDto;
import project.momento.room.dto.RoomDto;
import project.momento.room.mapper.RoomMapper;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct; // 임시로 채용한 어
import java.util.*;

@Service
public class RoomService {
	
	public Map<String, RoomDto> roomDtoMap; // 임시적으로 구현한 채팅방 저장을 위한 Map
	
	@PostConstruct
	private void init() {
		roomDtoMap = new LinkedHashMap<>();
	} // init()를 통한 Map 초기화
	
	public List<RoomDto> findAllRooms(String roomStatus) {
		List<RoomDto> rooms = new ArrayList<>(roomDtoMap.values());
		List<RoomDto> result = new ArrayList<>();
		for(RoomDto room : rooms)
		{
			if (roomStatus == null || roomStatus.equals(""))
			{
				result.add(room);
			}
			else if (Integer.parseInt(roomStatus) == (room.getIsRunning()))
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
	
	public RoomDto createRoomDto(RoomDto roomDto, LoginDto loginDto) {
		roomDto.setPkRoomSeq(UUID.randomUUID().toString());
		if(roomDto.getTeamNumber() != 0)
			roomDto.setTotal(roomDto.getRoomNumber()*roomDto.getTeamNumber());
		else
			roomDto.setTotal(roomDto.getRoomNumber());
		roomDto.setParticipants(0);
		roomDto.setIsRunning(0);
		Integer[] intArray = new Integer[36];
		for(int i=0; i < 36; i++)
			intArray[i] = i+1;
		
		List<Integer> intList = Arrays.asList(intArray);
		Collections.shuffle(intList);
		roomDto.setShuffleNo(intList);
//		RoomDto room = RoomDto.create(roomDto);
		roomDtoMap.put(roomDto.getPkRoomSeq(), roomDto);
		roomDto.setOwner(loginDto.getPkUserSeq());
		
		return roomDto;
	} // 방 생성

	public void addUser(ChatDto message) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(message.getPkRoomSeq());
		// 현재는 임시로 생성해 두었지만 실제로 완성시 세션의 유저고유번호를 저장해야함
		if(room.getUserList().get("waitList") == null)
		{
			HashMap dump = new HashMap();
			dump.put("dump", "dump");
			room.getUserList().put("waitList", dump);
			room.getUserList().get("waitList").put(message.getPkUserSeq(), message.getUserName());
			room.getUserList().get("waitList").remove("dump");
		}else {
			room.getUserList().get("waitList").put(message.getPkUserSeq(), message.getUserName());
		}
		
		room.setParticipants(room.getParticipants() + 1);
	}
	
	public void delUser(String roomId, int pkUserSeq, String team) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(roomId);
		room.getUserList().get(team).remove(pkUserSeq);
		room.setParticipants(room.getParticipants() - 1);
		if(room.getOwner() == pkUserSeq) {
			roomDtoMap.remove(roomId);
		}else if((room.getUserList().get("waitList") == null || room.getUserList().get("waitList").isEmpty()) 
        		&& (room.getUserList().get("team1") == null || room.getUserList().get("team1").isEmpty()) 
        		&& (room.getUserList().get("team2") == null || room.getUserList().get("team2").isEmpty())
        		&& (room.getUserList().get("team3") == null || room.getUserList().get("team3").isEmpty()) 
        		&& (room.getUserList().get("team4") == null || room.getUserList().get("team4").isEmpty()))
        {
        	roomDtoMap.remove(roomId);
        }
	}

	public HashMap<String, String> getUserList(ChatDto message) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(message.getPkRoomSeq());
		HashMap<String, String> userList = room.getUserList().get("waitList");
		return userList;
	}

	public String getUserName(String roomId, int pkUserSeq, String team) {
		// TODO Auto-generated method stub
		RoomDto room = roomDtoMap.get(roomId);
		String userName = (String) room.getUserList().get(team).get(pkUserSeq);

        return userName;
	}
// DB 연동시 사용 
//	@Autowired
//	private RoomMapper roomMapper;
//	
//	public void insertRoom(RoomDto rmDto) {
//		// TODO Auto-generated method stub
//		roomMapper.insertRoom(rmDto);
//	}
//
//    /**
//     * 모든 방 리스트를 가져옵니다.
//     *
//     * @return 방 리스트를 담고 있는 RoomDto 객체의 리스트
//     */
//    public List<RoomDto> getAllRooms() {
//        return roomMapper.getAllRooms();
//    }
//
//    /**
//     * 주어진 pkRoomSeq에 해당하는 방을 조회합니다.
//     *
//     * @param pkRoomSeq 조회할 방의 고유번호
//     * @return 조회된 방 정보를 담고 있는 RoomDto 객체의 리스트
//     */
//    public List<RoomDto> getRoomByPkRoomSeq(int pkRoomSeq) {
//        return roomMapper.getRoomByPkRoomSeq(pkRoomSeq);
//    }
//
//    /**
//     * 주어진 pkRoomSeq에 해당하는 방을 삭제합니다.
//     *
//     * @param pkRoomSeq 삭제할 방의 고유번호
//     */
//    public void deleteRoomByPkRoomSeq(int pkRoomSeq) {
//    	roomMapper.deleteRoomByPkRoomSeq(pkRoomSeq);
//    }
}

