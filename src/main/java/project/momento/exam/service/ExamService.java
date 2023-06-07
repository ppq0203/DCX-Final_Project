package project.momento.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.education.dto.EducationDto;
import project.momento.education.mapper.EducationMapper;
import project.momento.exam.dto.ExamDto;
import project.momento.exam.mapper.ExamMapper;
import project.momento.page.Criteria;

@Service
public class ExamService {
	
	@Autowired
	private ExamMapper examMapper;

	public void insertExam(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.insertExam(examDto);
	}

	
	
}