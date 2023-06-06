package project.momento.testcase.mapper;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.login.dto.LoginDto;
import project.momento.sign.dto.SignDto;
import project.momento.testcase.dto.TestcaseDto;

@Mapper
public interface TestcaseMapper {
	
	void testCaseToDbList(List<TestcaseDto> testcaseList);

	void testCaseToDbDto(TestcaseDto testcaseDto);
	
}