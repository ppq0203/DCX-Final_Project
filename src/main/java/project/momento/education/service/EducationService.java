package project.momento.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.education.dto.EducationDto;
import project.momento.education.mapper.EducationMapper;
import project.momento.page.Criteria;

@Service
public class EducationService {
	
	@Autowired
	private EducationMapper subjectMapper;

	public EducationDto selectEducation(EducationDto subjectDto) {
		// TODO Auto-generated method stub
		
		return subjectMapper.selectEducation(subjectDto);
	}

	public List<EducationDto> selectEducationList(Criteria cri) {
		// TODO Auto-generated method stub
		return subjectMapper.selectEducationList(cri);
	}

	public int selectEducationCount(Criteria cri) {
		// TODO Auto-generated method stub
		return subjectMapper.selectEducationCount(cri);
	}
}
