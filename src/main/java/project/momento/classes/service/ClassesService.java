package project.momento.classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.classes.mapper.ClassesMapper;
import project.momento.classes.dto.ClassesDto;

@Service
public class ClassesService {
	
	@Autowired
	private ClassesMapper classesMapper;

	public void insertClasses(ClassesDto classesDto) {
		// TODO Auto-generated method stub
		classesMapper.insertClasses(classesDto);
	}

	public ClassesDto selectClasses(ClassesDto classesDto) {
		// TODO Auto-generated method stub
		return classesMapper.selectClasses(classesDto);
	}

	public void updateClasses(ClassesDto classesDto) {
		// TODO Auto-generated method stub
		classesMapper.updateClasses(classesDto);
	}

	public List<ClassesDto> getClassesList(int pkSubjectSeq) {
		// TODO Auto-generated method stub
		return classesMapper.getClassList(pkSubjectSeq);
	}

	public void deleteClass(ClassesDto classesDto) {
		// TODO Auto-generated method stub
		classesMapper.deleteClass(classesDto);
	}

}
