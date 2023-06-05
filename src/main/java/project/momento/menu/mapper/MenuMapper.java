package project.momento.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.menu.dto.MenuDto;

@Mapper
public interface MenuMapper {
	
	// 메뉴를 생성, 수정, 삭제를 반영
	void menuManagement(MenuDto menuManageDto);
	// 해당 유저 권한의 메뉴 중 사용중인 메뉴만 출력 
	List<Object> getUserMenuList(int pkAuthSeq);
	// 모든 메뉴 중 삭제되지 않은 메뉴만 출력
	List<Object> getAdminMenuList(int pkAuthSeq);

}