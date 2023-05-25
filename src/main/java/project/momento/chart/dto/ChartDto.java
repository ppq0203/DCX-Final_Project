package project.momento.chart.dto;
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */
/* sign.html에 있는 가져오는 정보의 이름를 연결해주는 곳 */

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ChartDto extends LoginDto {
	
	private int returnUserSeq;
	
	private int pkUserSeq;
	
	private String pkResultDetailSeq;
	
	private String pkResultSeq;          

}