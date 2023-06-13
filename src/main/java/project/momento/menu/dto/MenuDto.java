package project.momento.menu.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*메뉴관리*/
public class MenuDto {
	
	/*메뉴 고유식별 번호*/
	private int pkMenuSeq;
	/*권한 고유 식별번호*/
	private int pkAuthSeq;
	/*상위 메뉴번호*/
	private int upperMenuNo;
	/*메뉴 등급*/
	private int level;
	/*메뉴명*/
	private String menuName;
	/*링크 URL*/
	private String linkUrl;
	/*Controller path*/
	private String path;
	/*새창여부*/
	private String target;
	/*사용여부*/
	private String useYn;
	/*삭제여부*/
	private String delYn;
	/*등록자 아이디*/
	private String registId;
	/*등록일자*/
	private String registDt;
	/*수정자 아이디*/
	private String updateId;
	/*수정일자*/
	private String updateDt;

}