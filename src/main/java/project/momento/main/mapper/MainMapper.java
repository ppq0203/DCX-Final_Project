package project.momento.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.main.dto.MainDto;
import project.momento.menu.dto.MenuDto;
import project.momento.question.dto.QuestionDto;

@Mapper
public interface MainMapper {


	MainDto selectGameChart(MainDto mainDto);

	MainDto selectAiChart(MainDto mainDto);
	

}