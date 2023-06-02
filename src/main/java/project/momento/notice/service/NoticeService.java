package project.momento.notice.service;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.menu.dto.MenuDto;
import project.momento.menu.mapper.MenuMapper;
import project.momento.sign.dto.SignDto;
import project.momento.sign.mapper.SignMapper;

@Service
public class NoticeService {
	
	@Autowired
	private MenuMapper menuMapper;
	
	// 메뉴를 생성, 수정, 삭제를 반영
	public void menuManagement(MenuDto menuManageDto) {
		menuMapper.menuManagement(menuManageDto);
	}
	
	// 메뉴 목록을 불러오는 함수
	public List<MenuDto> getMenuList(int pkAuthSeq) {
		// 관리자인지 확인
		if (pkAuthSeq != 1) {
			// 관리자가 아닌 경우
			return menuMapper.getUserMenuList(pkAuthSeq);
		} else {
			// 관리자인 경우
			return menuMapper.getAdminMenuList(pkAuthSeq);
		}
	}

}
