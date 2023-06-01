package project.momento.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import project.momento.login.dto.LoginDto;
import project.momento.login.mapper.LoginMapper;

@Transactional
@RequiredArgsConstructor /* 단위생성자(?)*/
@Service
public class LoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	public LoginDto checkLogin(LoginDto loginDto) {
		return loginMapper.checkLogin(loginDto);
	}
	
	public List<String> userList(LoginDto loginDto)
	{
		return loginMapper.userList(loginDto);
	}
	
	public void insertUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		
		loginMapper.insertUser(loginDto);
	}
	public LoginDto updateUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		
		return loginMapper.updateUser(loginDto);
	}
	public void deleteUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		
		loginMapper.deleteUser(loginDto);
	}
	public LoginDto selectUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		
		return loginMapper.selectUser(loginDto);
	}
	public LoginDto userYn(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return loginMapper.userYn(loginDto);
	}
//	public int getIDPassCheck(String id, String pwd) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public String getName(String myid) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}