package project.momento.notice.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import project.momento.notice.dto.NoticeDto;
import project.momento.page.Criteria;

@Repository
@Mapper
public interface NoticeMapper {

	void insertNotice(NoticeDto noticeDto);

	List<NoticeDto> selectNoticeList(Criteria cri);

	int selectNoticeListCount(Criteria cri);

}	
	
	
