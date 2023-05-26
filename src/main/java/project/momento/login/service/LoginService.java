package project.momento.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import project.momento.login.dto.LoginDto;
import project.momento.login.mapper.LoginMapper;
import project.momento.sign.dto.SignDto;

@Transactional
@RequiredArgsConstructor /* 단위생성자(?)*/
@Service
public class LoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	public LoginDto checkLogin(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return loginMapper.checkLogin(loginDto);
	}
	
	public List<String> userList(LoginDto loginDto)
	{
		return loginMapper.userList(loginDto);
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