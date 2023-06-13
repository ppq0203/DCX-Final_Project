package project.momento.question.function;

import java.util.List;

import project.momento.result.dto.ResultDto;

public class ResultClass {
	public static void resultDtoToResultInfo(List<ResultDto> listResultDto)
	{
		String sloveTime = listResultDto.get(0).getSloveTime();
		//single mode
		if (listResultDto.get(0).getMyTeamNum().equals("0"))
		{
			
		}
		// multi solo
		else if (listResultDto.get(0).getSloveTeamNum().equals("-1"))
		{
			
		}
		// multi team
		else
		{
			
		}
	}
}
