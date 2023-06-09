package project.momento.room.controller;

import project.momento.room.dto.RoomDto;
import project.momento.room.service.RoomService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

import lombok.Data;

@Controller
public class RoomController {
    
	@Autowired
	private RoomService roomService;

    /**
     * 방 생성 API
     *
     * @param roomDto 생성할 방 정보를 담고 있는 RoomDto 객체
     */
 /**
  * @GetMapping
    public String insertRoom() {
    	RoomDto roomDto = new RoomDto();
    	roomDto.setPkRoomSeq(1);
    	roomDto.setRoomNo(1);
    	roomDto.setRoomName("2");
    	roomDto.setRoomPwd(0);
    	roomDto.setRoomType("22");
    	roomDto.setRoomNumber(0);
    	roomDto.setRoomId("1");
    	roomDto.setRoomTime("2023-06-08 19:30:15");
    	roomDto.setRoomPlaytime("2");
    	roomDto.setTeamNumber("1");
    	roomDto.setRoomLevel("1");
    	
        roomService.insertRoom(roomDto);
        return "index";
    }
    */
 
    /**
     * 모든 방 리스트 조회 API
     *
     * @return 방 리스트를 담고 있는 RoomDto 객체의 리스트

    @GetMapping("/jhtest")
   public List<RoomDto> getAllRooms() {
    	List<RoomDto> rooms = roomService.getAllRooms();
        System.out.println(rooms); // 출력 확인
        return rooms;
  }
  */
    

//
//    /**
//     * 특정 방 조회 API
//     *
//     * @param pkRoomSeq 조회할 방의 고유번호
//     * @return 조회된 방 정보를 담고 있는 RoomDto 객체의 리스트
//     */
//	@GetMapping("/jhtest")
//	public List<RoomDto> getRoomByPkRoomSeq() {
//	    int pkRoomSeq = 1; // 	를 1로 설정
//	    List<RoomDto> room = roomService.getRoomByPkRoomSeq(pkRoomSeq);
//	    System.out.println(room); // 출력 확인
//	    return room;
//	}
//
//    /**
//     * 특정 방 삭제 API
//     *
//     * @param pkRoomSeq 삭제할 방의 고유번호
	//     */
		@RequestMapping
		public void deleteRoomByPkRoomSeq() {
		    int pkRoomSeq = 1;
		    roomService.deleteRoomByPkRoomSeq(pkRoomSeq);
		}

}