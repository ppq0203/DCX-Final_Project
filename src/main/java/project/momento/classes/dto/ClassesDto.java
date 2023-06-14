package project.momento.classes.dto;
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import project.momento.file.dto.FileDto;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ClassesDto extends FileDto {
	
	private int pkClassSeq;
	
	private int pkSubjectSeq;
	
	private int pkFileClassSeq;
	
	private int pkFileHomeworkSeq;
	
	private String className;          
	
	private String classDt;          
	
	private String registId;          
	
	private String registDt;          
	
	private String updateId;
	
	private String updateDt;          

}