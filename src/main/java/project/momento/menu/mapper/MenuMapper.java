package project.momento.menu.mapper;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.login.dto.LoginDto;
import project.momento.menu.dto.MenuDto;
import project.momento.sign.dto.SignDto;

@Mapper
public interface MenuMapper {
	
	// 메뉴를 생성, 수정, 삭제를 반영
	void menuManagement(MenuDto menuManageDto);
	// 해당 유저 권한의 메뉴 중 사용중인 메뉴만 출력 
	List<MenuDto> getUserMenuList(int pkAuthSeq);
	// 모든 메뉴 중 삭제되지 않은 메뉴만 출력
	List<MenuDto> getAdminMenuList(int pkAuthSeq);

}