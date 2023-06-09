package project.momento.exam.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.file.dto.FileDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*출결관리*/
public class ExamDto extends FileDto {
	
	private int pkExamSeq;
    private int pkSubjectSeq;
    private String title;
    private String contents;
    private String registId;
    private String registDt;
    private String updateId;
    private String updateDt;
    private int pkFileSeq;
    private int returnId;

}