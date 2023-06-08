package project.momento.sign.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.sign.dto.SignDto;

@Mapper
public interface SignMapper {
	
	void insertUser(SignDto signDto);
	
	List<SignDto> waitList(SignDto signDto);
	
	//아이디 중복체크
	int checkButton(SignDto signDto);

	void signAlluser();

	void denyUser(SignDto signDto);

	void denyAlluser();

	

	
	
}