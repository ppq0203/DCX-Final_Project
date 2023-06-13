package project.momento.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import project.momento.main.dto.MainDto;
import project.momento.main.mapper.MainMapper;
import project.momento.question.dto.QuestionDto;


@RequiredArgsConstructor /* 단위생성자(?)*/
@Service
public class MainService {
	
	@Autowired
	private MainMapper mainMapper;


	public MainDto selectGameChart(MainDto mainDto) {
		// TODO Auto-generated method stub
		return mainMapper.selectGameChart(mainDto);
	}


	public MainDto selectAiChart(MainDto mainDto) {
		// TODO Auto-generated method stub
		return mainMapper.selectAiChart(mainDto);
	}


	public MainDto selectExamChart(MainDto mainDto) {
		// TODO Auto-generated method stub
		return mainMapper.selectExamChart(mainDto);
	}
	

	
}