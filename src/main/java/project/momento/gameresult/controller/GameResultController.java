package project.momento.gameresult.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.momento.gameresult.dto.GameResultDto;
import project.momento.gameresult.service.GameResultService;

@Controller
public class GameResultController {
	@Autowired	
	private GameResultService gameResultService;
	@GetMapping("/jhtest")
	public String insertGameResult() {
		GameResultDto gameResultDto = new GameResultDto();
		gameResultDto.setPkQuestionSeq(1);
		gameResultDto.setMyTeamNum(1);
		gameResultDto.setSloveTeamNum("1");
		gameResultDto.setMyResult(4);
		gameResultDto.setQuestionType("ff");
		
		
	    gameResultService.insertGameResult(gameResultDto);
	    return "index";
	}
	
}
