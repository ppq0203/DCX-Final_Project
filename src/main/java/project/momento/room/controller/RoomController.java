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

import project.momento.room.service.RoomService; // 임시

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/chat")
public class RoomController {

	private final RoomService service;
	
	
	// 채팅방 목록 조회
	@GetMapping(value = "/rooms")
	public ModelAndView rooms() {
		
		log.info(" # All Chat Rooms");
		ModelAndView mv = new ModelAndView("content/rooms");
		
		mv.addObject("list", service.findAllRooms());
		
		return mv;
	}
	
	// 채팅방 개설
	@PostMapping(value = "/room")
	public String create(@RequestParam String name, RedirectAttributes rttr) {
		
		log.info("# Create Chat Room , name: " + name);
		rttr.addFlashAttribute("roomName", service.createRoomDto(name));
		
        return "redirect:/chat/rooms";
	}
	
	// 채팅방 조회
	@GetMapping("/room")
	public void getRoom(String roomId, Model model) {
        log.info("# get Chat Room, roomID : " + roomId);

        model.addAttribute("room", service.findRoomById(roomId));
	}
}