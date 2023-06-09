package project.momento.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.notice.dto.NoticeDto;
import project.momento.notice.mapper.NoticeMapper;
import project.momento.page.Criteria;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	public void insertNotice(NoticeDto noticeDto) {
		// TODO Auto-generated method stub
		noticeMapper.insertNotice(noticeDto);
	}

	public List<NoticeDto> selectNoticeList(Criteria cri) {
		// TODO Auto-generated method stub
		return noticeMapper.selectNoticeList(cri);
	}

	public int selectNoticeListCount(Criteria cri) {
		// TODO Auto-generated method stub
		return noticeMapper.selectNoticeListCount(cri);
	}

	public List<NoticeDto> getNoticeList(int pkEducationSeq) {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeList(pkEducationSeq);
	}

	public NoticeDto selectNotice(String pkNoticeSeq) {
		// TODO Auto-generated method stub
		return noticeMapper.selectNotice(pkNoticeSeq);
	}

}
