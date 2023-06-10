package project.momento.result.dto;


import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class ResultDto {
	
	int pkUserSeq;
	
	String sloveTime;
	
	int PK_questionSeq;
	
	String myTeamNum;
	
	String sloveTeamNum;
	
	String myResult;
	
	String problemType;
}
