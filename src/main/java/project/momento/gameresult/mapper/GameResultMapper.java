package project.momento.gameresult.mapper;

import project.momento.gameresult.dto.GameResultDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GameResultMapper {
    void insertGameResult(GameResultDto resultDTO);
    List<String> findDistinctSolveTimeByUserSeq(int userSeq);
    List<GameResultDto> findResultsByUserSeqAndSolveTime(int userSeq, String solveTime);
}
