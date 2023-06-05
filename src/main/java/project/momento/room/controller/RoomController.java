package project.momento.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import project.momento.room.dto.RoomDto;
import project.momento.room.mapper.RoomMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

	private final RoomMapper mapper;
	
	
	// 채팅방 목록 조회
	@GetMapping(value = "/rooms")
	public ModelAndView rooms() {
		
		log.info(" # All Chat Rooms");
		ModelAndView mv = new ModelAndView("chat/rooms");
		
		mv.addObject("list", mapper.findAllRooms());
		
		return mv;
	}
	
	// 채팅방 개설
	@PostMapping(value = "/room")
	public ModelAndView create(String roomName) {
		
		ModelAndView mv = new ModelAndView("chat/room");
		
		mv.addObject("list", mapper.createRoom(roomName));
		
		return mv;
	}
	
	// 채팅방 조회
	@GetMapping("/room")
	public void findRoom(String roomName, Model model) {
		model.addAttribute("room", mapper.findRoomByName(roomName));
	}
}