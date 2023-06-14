package project.momento.classes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.classes.dto.ClassesDto;


@Mapper
public interface ClassesMapper {

	void insertClasses(ClassesDto classesDto);
	
	ClassesDto selectClasses(ClassesDto classesDto);

	void updateClasses(ClassesDto classesDto);

	List<ClassesDto> getClassList(int pkSubjectSeq);

	void deleteClass(ClassesDto classesDto);

}