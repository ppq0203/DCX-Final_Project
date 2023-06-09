package project.momento.roomlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.roomlog.dto.RoomLogDto;
import project.momento.roomlog.mapper.RoomLogMapper;


@Service
public class RoomLogService {
	
	@Autowired
	private RoomLogMapper RoomLogMapper;
	
	// 데이터 저장
	public void insertRoomLog(RoomLogDto roomlogDto) {
		RoomLogMapper.insertRoomLog(roomlogDto);
	}
	
	//데이터 불러오기 매칭번호
	public List<RoomLogDto> selectRoomLogMapper(int pkRoomLogSeq){
		return RoomLogMapper.selectRoomLog(pkRoomLogSeq);
	
	}
	//방번호로 삭제하기
	public void deleteRoomLog(int pkRoomLogSeq) {
		// TODO Auto-generated method stub
		RoomLogMapper.deleteRoomLog(pkRoomLogSeq);
	}
	//유저 번호로 삭제하기
	public void deleteRoomLog2(int pkUserSeq) {
		// TODO Auto-generated method stub
		RoomLogMapper.deleteRoomLog2(pkUserSeq);
	}
}