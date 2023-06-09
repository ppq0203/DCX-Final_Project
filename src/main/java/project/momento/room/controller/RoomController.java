package project.momento.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import project.momento.question.dto.QuestionDto;
import project.momento.question.mapper.QuestionMapper;
import project.momento.question.service.QuestionService;
import project.momento.question.service.TestcaseService;
import project.momento.room.dto.RoomDto;
import project.momento.room.mapper.RoomMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.momento.room.service.RoomService; // 임시

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/chat")
public class RoomController {

	private final RoomService service;
	

	@Autowired
	private QuestionService questionService;
	
	// 채팅방 목록 조회
	@GetMapping(value = "/rooms")
	public ModelAndView rooms(String roomType) {
		
		log.info(" # All Chat Rooms");
		ModelAndView mv = new ModelAndView("content/gameWaiting");
		mv.addObject("list", service.findAllRooms(roomType));
		
		return mv;
	}
	
	// 채팅방 개설
	@PostMapping(value = "/room")
	public String create(@ModelAttribute("RoomDto") RoomDto roomDto) {
		System.out.println(roomDto);
		log.info("# Create Chat Room , name: " + roomDto.getRoomName());
		service.createRoomDto(roomDto);
//		rttr.addFlashAttribute("roomName", service.createRoomDto(name));
		
        return "redirect:/chat/rooms";
	}
	
	// 채팅방 들어갈 시
	@PostMapping("/enterRoom")
	public ModelAndView getRoom(String pkRoomSeq, String level, String questionNum) {
		level = "4";
		questionNum = "36";
		
		QuestionDto qtDto = new QuestionDto();
		String[] levels = null;
		if(level.equals("4"))
			levels = new String[]{"1","2","3"};
		else
			levels = new String[]{level};
		qtDto.setLevels(levels);
		qtDto.setProbNum(Integer.parseInt(questionNum));
		List<QuestionDto> questionList= questionService.selectQuestion(qtDto);
		System.out.println(questionList.size());
		
        log.info("# get Chat Room, roomSeq : " + pkRoomSeq);
		ModelAndView mv = new ModelAndView("content/room");
		
		mv.addObject("questionList", questionList);
		mv.addObject("room", service.findRoomById(pkRoomSeq));
		return mv;
	}
	
}