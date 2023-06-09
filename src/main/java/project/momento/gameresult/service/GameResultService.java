package project.momento.gameresult.service;

import project.momento.gameresult.dto.GameResultDto;
import project.momento.gameresult.mapper.GameResultMapper;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GameResultService {
	private final GameResultMapper resultMapper;
	
	public GameResultService(GameResultMapper gameResultMapper) {
		this.resultMapper = gameResultMapper;
	}
	
	public void insertGameResult(GameResultDto resultDTO) {
		resultMapper.insertGameResult(resultDTO);
    }
	
    public List<String> findDistinctSolveTimeByUserSeq(int userSeq) {
        return resultMapper.findDistinctSolveTimeByUserSeq(userSeq);
    }
}
