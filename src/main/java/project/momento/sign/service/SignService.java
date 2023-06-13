package project.momento.sign.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.login.dto.LoginDto;
import project.momento.sign.dto.SignDto;
import project.momento.sign.mapper.SignMapper;

@Service
public class SignService {
	
	@Autowired
	private SignMapper SignMapper;

	public void insertUser(SignDto signDto) {
		// TODO Auto-generated method stub
		SignMapper.insertUser(signDto);
	}
	
	public void insertManager(SignDto signDto) {
		// TODO Auto-generated method stub
		SignMapper.insertManager(signDto);
	}
	
	public List<SignDto> waitList(SignDto signDto){
		return SignMapper.waitList(signDto);
	}

	//아이디 중복체크
	public int checkButton(SignDto signDto) {
		// TODO Auto-generated method stub
		int cnt = SignMapper.checkButton(signDto);
		return cnt;
	}

	public void signAlluser() {
		// TODO Auto-generated method stub
		SignMapper.signAlluser();
	}

	public void denyUser(SignDto signDto) {
		// TODO Auto-generated method stub
		SignMapper.denyUser(signDto);
	}

	public void denyAlluser() {
		// TODO Auto-generated method stub
		SignMapper.denyAlluser();
	}

}
