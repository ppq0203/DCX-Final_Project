package project.momento.main.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.login.dto.LoginDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*풀이 답*/
public class MainDto extends LoginDto {
	
	/*학생 최고점수*/
	private float maxVal;
	/*학생 최저점수*/
	private float minVal;
	/*학생 평균점수*/
	private float avgVal;
	/*학생 점수*/
	private float userVal;
	/*틀린 갯숫*/
	private int xValue;
	/*맞은 갯숫*/
	private int oValue;
	
	private String color;
}