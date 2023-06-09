package project.momento.room.service;

import project.momento.room.dto.RoomDto;
import project.momento.room.mapper.RoomMapper;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RoomService {
    private final RoomMapper Mapper;

    public RoomService(RoomMapper roomMapper) {
        this.Mapper = roomMapper;
    }

    /**
     * 방을 생성합니다.
     *
     * @param roomDto 생성할 방 정보를 담고 있는 RoomDto 객체
     */
    public void insertRoom(RoomDto roomDto) {
        Mapper.insertRoom(roomDto);
    }

    /**
     * 모든 방 리스트를 가져옵니다.
     *
     * @return 방 리스트를 담고 있는 RoomDto 객체의 리스트
     */
    public List<RoomDto> getAllRooms() {
        return Mapper.getAllRooms();
    }

    /**
     * 주어진 pkRoomSeq에 해당하는 방을 조회합니다.
     *
     * @param pkRoomSeq 조회할 방의 고유번호
     * @return 조회된 방 정보를 담고 있는 RoomDto 객체의 리스트
     */
    public List<RoomDto> getRoomByPkRoomSeq(int pkRoomSeq) {
        return Mapper.getRoomByPkRoomSeq(pkRoomSeq);
    }

    /**
     * 주어진 pkRoomSeq에 해당하는 방을 삭제합니다.
     *
     * @param pkRoomSeq 삭제할 방의 고유번호
     */
    public void deleteRoomByPkRoomSeq(int pkRoomSeq) {
        Mapper.deleteRoomByPkRoomSeq(pkRoomSeq);
    }
}
