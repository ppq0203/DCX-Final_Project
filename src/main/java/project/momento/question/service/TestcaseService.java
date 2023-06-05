package project.momento.question.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.login.dto.LoginDto;
import project.momento.question.dto.TestcaseDto;
import project.momento.question.mapper.TestcaseMapper;
import project.momento.sign.dto.SignDto;
import project.momento.sign.mapper.SignMapper;

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
	
	public List<TestcaseDto> selectTestcaseList(int qestionSeq) {
		
		return testcaseMapper.selectTestcaseList(qestionSeq);
	}

}
