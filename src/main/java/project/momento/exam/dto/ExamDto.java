package project.momento.exam.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*출결관리*/
public class ExamDto {
	
	private int pkExamSeq;
    private int pkSubjectSeq;
    private String title;
    private String contents;
    private String registId;
    private String registDt;
    private String updateId;
    private String updateDt;
    private int pkFileSeq;

}