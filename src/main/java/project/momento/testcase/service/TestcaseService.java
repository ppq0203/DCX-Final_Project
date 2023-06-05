package project.momento.testcase.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.login.dto.LoginDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.mapper.SignMapper;
import project.momento.testcase.dto.TestcaseDto;
import project.momento.testcase.mapper.TestcaseMapper;

@Service
public class TestcaseService {
	
	@Autowired
	private TestcaseMapper testcaseMapper;

	public void testCaseToDbList(List<TestcaseDto> testcaseList) {
		// TODO Auto-generated method stub
		testcaseMapper.testCaseToDbList(testcaseList);
	}

	public void testCaseToDbDto(TestcaseDto testcaseDto) {
		// TODO Auto-generated method stub
		testcaseMapper.testCaseToDbDto(testcaseDto);
	}


}
