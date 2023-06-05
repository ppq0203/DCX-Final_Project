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
	private EducationMapper educationMapper;

	public List<EducationDto> selectEducation(EducationDto subjectDto) {
		// TODO Auto-generated method stub
		
		return educationMapper.selectEducation(subjectDto);
	}

	public List<EducationDto> selectEducationList(Criteria cri) {
		// TODO Auto-generated method stub
		return educationMapper.selectEducationList(cri);
	}

	public int selectEducationCount(Criteria cri) {
		// TODO Auto-generated method stub
		return educationMapper.selectEducationCount(cri);
	}

	public List<EducationDto> selectSubjectList(int pkEducationSeq) {
		// TODO Auto-generated method stub
		return educationMapper.selectSubjectList(pkEducationSeq);
	}

	public List<EducationDto> selectEducationStudList(int pkEducationSeq) {
		// TODO Auto-generated method stub
		return educationMapper.selectEducationStudList(pkEducationSeq);
	}

	public int insertEducation(EducationDto educationDto) {
		// educationDt 값을 쪼개서 subStartDt와 subEndDt로 설정
        String educationDt = educationDto.getEducationDt();
        String[] dates = educationDt.split(" ~ ");
        String subStartDt = dates[0];
        String subEndDt = dates[1];

        // 쪼개어진 값을 educationDto에 설정
        educationDto.setSubStartDt(subStartDt);
        educationDto.setSubEndDt(subEndDt);
		
		return educationMapper.insertEducation(educationDto);
	}

	public void insertEducationStud(EducationDto educationDto) {
		// TODO Auto-generated method stub
		educationMapper.insertEducationStud(educationDto);
	}

	public void insertSubject(EducationDto educationDto) {
		// educationDt 값을 쪼개서 subStartDt와 subEndDt로 설정
        String subjectDt = educationDto.getSubjectDt();
        String[] dates = subjectDt.split(" ~ ");
        String subStartDt = dates[0];
        String subEndDt = dates[1];

        educationDto.setSubStartDt(subStartDt);
        educationDto.setSubEndDt(subEndDt);
		educationMapper.insertSubject(educationDto);
	}

	public List<Object> getEducationList(int pkUserSeq) {
		return educationMapper.getEducationList(pkUserSeq);
	}

	public void updateEducation(EducationDto educationDto) {
		
		// educationDt 값을 쪼개서 subStartDt와 subEndDt로 설정
        String educationDt = educationDto.getEducationDt();
        String[] dates = educationDt.split(" ~ ");
        String subStartDt = dates[0];
        String subEndDt = dates[1];

        // 쪼개어진 값을 educationDto에 설정
        educationDto.setSubStartDt(subStartDt);
        educationDto.setSubEndDt(subEndDt);
		educationMapper.updateEducation(educationDto);
		
	}

	public void deleteEducationStud(int pkEducationSeq) {
		// TODO Auto-generated method stub
		educationMapper.deleteEducationStud(pkEducationSeq);
	}

	public void deleteSubject(int pkEducationSeq) {
		// TODO Auto-generated method stub
		educationMapper.deleteSubject(pkEducationSeq);
	}

	public void deleteEducation(int pkEducationSeq) {
		// TODO Auto-generated method stub
		educationMapper.deleteEducation(pkEducationSeq);
	}
	
}
