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

	public List<ExamDto> getExamList(ExamDto examDto) {
		// TODO Auto-generated method stub
		return examMapper.getExamList(examDto);
	}

	public void insertExamDetail(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.insertExamDetail(examDto);
	}

	public List<ExamDto> selectExamDetailList(ExamDto examDto) {
		// TODO Auto-generated method stub
		return examMapper.selectExamDetailList(examDto);
	}

	public void deleteExamDetail(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.deleteExamDetail(examDto);
	}

	public void deleteExam(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.deleteExam(examDto);
	}

	public void startExam(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.startExam(examDto);
	}

	public void insertResult(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.insertResult(examDto);
	}

	public List<ExamDto> getExamResultList(ExamDto examDto) {
		// TODO Auto-generated method stub
		return examMapper.getExamResultList(examDto);
	}

	public List<ExamDto> selectExamResultDetailList(ExamDto examDto) {
		// TODO Auto-generated method stub
		return examMapper.selectExamResultDetailList(examDto);
	}
	
	public List<ExamDto> selectExamResultDoneDetailList(ExamDto examDto) {
		// TODO Auto-generated method stub
		return examMapper.selectExamResultDetailList(examDto);
	}

	public void updateScore(ExamDto examDto) {
		// TODO Auto-generated method stub
		examMapper.updateScore(examDto);
	}

	
	
}
