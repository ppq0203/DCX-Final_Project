package project.momento.file.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*파일관리*/
public class FileDto {
		
	/*파일 고유 식별 번호*/
	private int pkFileSeq;
	/*파일 이름*/
	private String fileNm;
	/*파일 사이즈*/
	private int fileSize;
	/*파일 경로*/
	private String filePath;
	/*파일 다운로드 수*/
	private int fileDownCnt;
	/*파일 썸네일 가로 사이즈*/
	private int fileThumWidSize;
	/*파일 썸네일 세로 사이즈*/
	private int fileThumHeiSize;
	/*파일 확장자*/
	private String fileExtension;
	/*등록 아이디*/
	private String registId;
	/*등록 일자*/
	private String registDt;
	/*수정 아이디*/
	private String updateId;
	/*수정 일자*/
	private String updateDt;

}