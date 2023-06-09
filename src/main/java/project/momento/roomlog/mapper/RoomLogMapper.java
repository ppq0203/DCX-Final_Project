package project.momento.roomlog.mapper;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.roomlog.dto.RoomLogDto;

@Mapper
public interface RoomLogMapper {
	
	void insertRoomLog(RoomLogDto roomlogDto);

	List<RoomLogDto> selectRoomLog(int pkRoomLogSeq);

	void deleteRoomLog(int pkRoomLogSeq);
	
	void deleteRoomLog2(int pkUserSeq);
	

	

	
	
}