package project.momento.question.mapper;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.login.dto.LoginDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.sign.dto.SignDto;

@Mapper
public interface TestcaseMapper {
	
	void testCaseToDbList(List<TestcaseDto> testcaseList);
	void testCaseToDbDto(TestcaseDto testcaseDto);
	
	List<TestcaseDto> selectTestcaseList(int qestionSeq);
	
}