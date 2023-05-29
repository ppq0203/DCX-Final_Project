package project.momento.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import project.momento.login.dto.LoginDto;

@Repository
@Mapper
public interface LoginMapper {
	LoginDto checkLogin(LoginDto loginDto); /* Service에서 받아오고 Mapper.xml에보냄 */
	
	List<String> userList(LoginDto loginDto);
	
//	public int getIDPassCheck(String id, String pwd);
//
//	public String getName(String myid);

}	
	
	
