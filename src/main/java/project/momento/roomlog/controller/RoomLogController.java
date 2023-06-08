package project.momento.roomlog.controller;

import project.momento.answer.service.AnswerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import project.momento.roomlog.service.RoomLogService;

@Controller
public class RoomLogController {
    
	@Autowired
	private RoomLogService RoomLogService;
	

}