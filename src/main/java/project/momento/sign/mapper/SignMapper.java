package project.momento.sign.mapper;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.login.dto.LoginDto;
import project.momento.sign.dto.SignDto;

@Mapper
public interface SignMapper {
	
	void insertUser(SignDto signDto);
	
	List<String> waitList(SignDto signDto);
	//아이디 중복체크
	int checkButton(SignDto signDto);

	void signUser(SignDto signDto);
	
}