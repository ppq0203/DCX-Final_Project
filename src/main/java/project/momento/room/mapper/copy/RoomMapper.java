package project.momento.room.mapper.copy;

import org.apache.ibatis.annotations.Mapper;

import project.momento.room.dto.RoomDto;

@Mapper
public interface RoomMapper {

	RoomDto selectRoom(RoomDto roomtDto);											
				}