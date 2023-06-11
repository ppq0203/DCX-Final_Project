package project.momento.room.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
	public ModelAndView rooms(String roomType) {
		
		log.info(" # All Chat Rooms");
		ModelAndView mv = new ModelAndView("content/gameWaiting");
		mv.addObject("list", roomService.findAllRooms(roomType));
		
		return mv;
	}
	
	// 채팅방 개설
	@PostMapping(value = "/room")
	public String create(@ModelAttribute("RoomDto") RoomDto roomDto) {
		System.out.println(roomDto);
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
		System.out.println(roomDto.getQuestionList());
		roomService.createRoomDto(roomDto);
//		rttr.addFlashAttribute("roomName", service.createRoomDto(name));
		
        return "redirect:/chat/rooms";
	}
	
	// 채팅방 들어갈 시
	@PostMapping("/enterRoom")
	public ModelAndView getRoom(String pkRoomSeq) {
		
		RoomDto roomDto = roomService.findRoomById(pkRoomSeq);
		List<QuestionDto> questionList = roomDto.getQuestionList();
		System.out.println(questionList);
		
        log.info("# get Chat Room, roomSeq : " + pkRoomSeq);
		ModelAndView mv = new ModelAndView("content/room");
		
		mv.addObject("questionList", questionList);
		mv.addObject("room", roomDto);
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

		return roomInfo;
	}

	@RequestMapping(value = "/ybytest", produces = "application/text;charset=utf-8") /* value주소 이름 */
	public String ybytest() {
		// 데이터 저장
		RoomDto rmDto = new RoomDto();
		rmDto.setPkRoomSeq("0");
		rmDto.setRoomNo("0");
		rmDto.setRoomName("0");
		rmDto.setRoomPwd(0);
		rmDto.setRoomType("0");
		rmDto.setRoomPlay(0);
		rmDto.setRoomPlaytime("0");
		rmDto.setRoomNumber(0);
		rmDto.setTeamNumber(0);
		rmDto.setRoomLevel(0);
		rmDto.setRoomId("0");
		rmDto.setRoomTime("0");
		rmDto.setUpdateDt("0");
		roomService.insertRoom(rmDto);
		System.out.println();
//		/////////////
//		// 데이터 불러오기
//		List<RoomDto> room = service.selectRoom(0);
//		System.out.println(room);
//		/////////////
//		// 데이터 삭제
		return "index";
	}
	
}