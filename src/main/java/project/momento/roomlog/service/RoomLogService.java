package project.momento.roomlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


import project.momento.roomlog.mapper.RoomLogMapper;


@Service
public class RoomLogService {
	
	@Autowired
	private RoomLogMapper RoomLogMapper;
	
	
}