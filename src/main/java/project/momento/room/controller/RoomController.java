package project.momento.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import project.momento.login.dto.LoginDto;
import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.function.StringCodeCompile;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import jakarta.servlet.http.HttpServletRequest;
import project.momento.room.service.RoomService; // 임시

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/chat")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private QuestionService questionService;

	// 채팅방 목록 조회
	@GetMapping(value = "/rooms")
	public ModelAndView rooms(String roomStatus) {
		
		log.info(" # All Chat Rooms");
		ModelAndView mv = new ModelAndView("content/gameWaiting");
		mv.addObject("list", roomService.findAllRooms(roomStatus));
		
		return mv;
	}
	
	// 채팅방 개설
	@PostMapping(value = "/room")
	public String create(@ModelAttribute("RoomDto") RoomDto roomDto, RedirectAttributes re, HttpServletRequest request) {
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		log.info("# Create Chat Room , name: " + roomDto.getRoomName());
		
		String level = Integer.toString(roomDto.getRoomLevel());
		String questionNum = "36";
		
		QuestionDto qtDto = new QuestionDto();
		String[] levels = null;
		if(level.equals("4"))
			levels = new String[]{"1","2","3"};
		else
			levels = new String[]{level};
		qtDto.setLevels(levels);
		qtDto.setProbNum(Integer.parseInt(questionNum));
		
		roomDto.setQuestionList(questionService.selectQuestion(qtDto));
		int questionCount = roomDto.getQuestionList().size();
		while(questionCount < 36)
		{
			if(level.equals("1") || level.equals("3"))
			{
				level = "2";
			}
			else
			{
				level = "1";
			}

			levels = new String[]{level};
			qtDto.setLevels(levels);
			qtDto.setProbNum(Integer.parseInt(questionNum)-questionCount);
			List<QuestionDto> questionList = questionService.selectQuestion(qtDto);
			roomDto.getQuestionList().addAll(questionList);
			questionCount = roomDto.getQuestionList().size();
		}
		roomService.createRoomDto(roomDto, loginDto);
//		rttr.addFlashAttribute("roomName", service.createRoomDto(name));
		
		re.addFlashAttribute("roomDto", roomDto);
		
        return "redirect:/chat/enterRoom";
	}
	
	// 채팅방 들어갈 시
	@RequestMapping("/enterRoom")
	public ModelAndView getRoom(String pkRoomSeq, HttpServletRequest request) {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		RoomDto roomDto;
		if(flashMap != null) {
			roomDto = (RoomDto) flashMap.get("roomDto");
			request.getSession().setAttribute("roomSeq", roomDto.getPkRoomSeq());
		}
		else {
			if(pkRoomSeq != null)
			{
				roomDto = roomService.findRoomById(pkRoomSeq);
			}
			else
			{
				roomDto = roomService.findRoomById((String) request.getSession().getAttribute("roomSeq"));
			}
		}
		
		LoginDto user = (LoginDto)request.getSession().getAttribute("loginDto");
		
        log.info("# get Chat Room, roomSeq : " + pkRoomSeq);
		ModelAndView mv = new ModelAndView();
		
		if(roomDto == null) {
			mv.setViewName("redirect:/chat/rooms");
		}
		else {
			
			List<QuestionDto> questionList = roomDto.getQuestionList();
			mv.setViewName("content/room");
			mv.addObject("questionList", questionList);
			mv.addObject("room", roomDto);
			mv.addObject("user", user);
		}
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/userCheck")
	public HashMap<String, String> userCheck(@RequestParam Map<String, Object> param) {
		HashMap<String, String> roomInfo = new HashMap<String, String>();
		
		String roomSeq = (String) param.get("roomSeq");
		
		RoomDto room = roomService.findRoomById(roomSeq);
		
		roomInfo.put("participants", Integer.toString(room.getParticipants()));
		roomInfo.put("total", Integer.toString(room.getTotal()));
		roomInfo.put("isRunning", Integer.toString(room.getIsRunning()));

		return roomInfo;
	}
	
}