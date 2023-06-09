package project.momento.roomlog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.roomlog.dto.RoomLogDto;
import project.momento.roomlog.mapper.RoomLogMapper;
import project.momento.roomlog.service.RoomLogService;

@Controller
public class RoomLogController {
    
	
	@Autowired
	private RoomLogService RoomLogService;
	// 실행 결과 확인용//
	@RequestMapping(value = "/yctest", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String yctest() {
		// 데이터 저장
		RoomLogDto rlDto = new RoomLogDto();
		rlDto.setPkRoomLogSeq(0);
		rlDto.setPkRoomSeq(0);
		rlDto.setPkUserSeq(77);
		rlDto.setObserver(0);
//		RoomLogService.insertRoomLog(rlDto);
		System.out.println(rlDto);
		
		/////////////
		// 데이터 불러오기
//		List<RoomLogDto> roomlog = RoomLogService.selectRoomLogMapper(0);
//		System.out.println(roomlog);
		
		/////////////
		// 데이터 삭제
		// 방번호 해당 행 제거
//		RoomLogService.deleteRoomLog(rlDto.getPkRoomLogSeq());

		// 방에 있는 유저가 나갔을때 제거
		RoomLogService.deleteRoomLog2(rlDto.getPkUserSeq());

		return "index";
	}
		
	
}