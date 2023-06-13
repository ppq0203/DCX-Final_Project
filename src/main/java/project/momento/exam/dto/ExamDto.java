package project.momento.exam.dto;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import project.momento.file.dto.FileDto;
import project.momento.subject.dto.SubjectDto;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*출결관리*/
public class ExamDto extends SubjectDto {
	
	private int pkExamSeq;
    private int pkSubjectSeq;
    private int pkExamDetailSeq;
    private int pkExamResultSeq;
    private int[] pkExamDetailSeqArray;
    private int pkUserSeq;
    private String title;
    private String contents;
    private String examDivn;
    private String registId;
    private String registDt;
    private String updateId;
    private String updateDt;
    private String answer;
    private String userDivn;
    private String ansResult;
    private String resultDivn;
    private int pkFileSeq;
    private int returnId;

}