package project.momento.classes.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.momento.classes.dto.ClassesDto;
import project.momento.classes.service.ClassesService;
import project.momento.file.dto.FileDto;
import project.momento.file.service.FileService;
import project.momento.login.dto.LoginDto;
import project.momento.subject.dto.SubjectDto;

@Controller
public class ClassesController {
	
	@Autowired 
	private FileService fileService;
	@Autowired 
	private ClassesService classesService;
	 
	@PostMapping(value = "/{userDivn}/classes/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String classesCreate(@RequestParam("classFilePath") List<MultipartFile> classFiles,
			@RequestParam("homeworkFilePath") List<MultipartFile> homeworkFiles,
			@RequestParam("className") List<String> classNames, @RequestParam("classDate") List<String> classDates,
			@PathVariable String userDivn, HttpServletRequest request) {
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		String userId = loginDto.getUserId();
		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		try {
			for (int i = 0; i < classNames.size(); i++) {
				// pkFileClassSeq, pkFileHomeworkSeq, className, classDt
				String className = classNames.get(i);
				if (!(className.equals(""))) {
					ClassesDto classesDto = new ClassesDto();
					MultipartFile classFile = classFiles.get(i);
					MultipartFile homeworkFile = homeworkFiles.get(i);
					String classDate = classDates.get(i);
					if (!classFile.isEmpty()) {
						int classFileSeq = fileService.uploadFile(classFile);
						classesDto.setPkFileClassSeq(classFileSeq);
					}
					if (!homeworkFile.isEmpty()) {
						int homeworkFileSeq = fileService.uploadFile(homeworkFile);
						classesDto.setPkFileHomeworkSeq(homeworkFileSeq);
					}
					classesDto.setClassName(className);
					classesDto.setClassDt(classDate);
					classesDto.setPkSubjectSeq(pkSubjectSeq);
					classesDto.setRegistId(userId);
					ClassesDto existsClasses = classesService.selectClasses(classesDto);
					if (!(existsClasses == null)) {
						FileDto fileDto = fileService.selectFile(existsClasses.getPkFileClassSeq());
						if (existsClasses.getPkFileClassSeq() != 0) {
							fileService.deleteFile(fileDto);
						}
						fileDto = fileService.selectFile(existsClasses.getPkFileHomeworkSeq());
						if (existsClasses.getPkFileHomeworkSeq() != 0) {
							fileService.deleteFile(fileDto);
						}
						classesService.updateClasses(classesDto);
					} else {
						classesService.insertClasses(classesDto);
					}
				}
			}
			return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/subject/main";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/subject/main";
		}
	}
	
	@GetMapping(value="/{userDivn}/download/{pkFileSeq}/file", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public void DownloadFile(@PathVariable int pkFileSeq, @PathVariable String userDivn, HttpServletResponse response) throws IOException {
		FileDto fileDto = fileService.selectFile(pkFileSeq);
	    if (fileDto == null) {
	        // 파일이 존재하지 않을 경우 예외 처리
	        throw new FileNotFoundException("File not found with ID: " + pkFileSeq);
	    }
	    String downloadDir="/file";
	    
	    // 파일명 설정
	    String fileName = fileDto.getFileNm();
	    String filePath;

	    // 파일 경로 생성
	    if (fileService.isWindows()) {
		    filePath = "C:" + File.separator + "file" + File.separator + fileName;
		} else {
		    filePath = File.separator + "file" + File.separator + fileName;
		}

		Path downloadPath = Path.of(downloadDir).resolve(filePath);

	    // 파일 다운로드를 위한 헤더 설정
	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

	    try (OutputStream outputStream = response.getOutputStream()) {
	        Files.copy(downloadPath, outputStream);
	        outputStream.flush();
	    }
	}
	
	@RequestMapping(value="/{userDivn}/classes/{classDt}/delete", produces="application/text;charset=utf-8") /* value주소 불러오기 이름*/
	public String DeleteClass(@PathVariable String classDt, @PathVariable String userDivn, HttpServletRequest request) {
		SubjectDto subjectDto = (SubjectDto) request.getSession().getAttribute("subjectDto");
		int pkSubjectSeq = subjectDto.getPkSubjectSeq();
		ClassesDto classesDto = new ClassesDto();
		classesDto.setPkSubjectSeq(pkSubjectSeq);
		classesDto.setClassDt(classDt);
		ClassesDto existsClasses = classesService.selectClasses(classesDto);
		try {
			if (!(existsClasses == null)) {
				FileDto fileDto = fileService.selectFile(existsClasses.getPkFileClassSeq());
				if (existsClasses.getPkFileClassSeq() != 0) {
					fileService.deleteFile(fileDto);
				}
				fileDto = fileService.selectFile(existsClasses.getPkFileHomeworkSeq());
				if (existsClasses.getPkFileHomeworkSeq() != 0) {
					fileService.deleteFile(fileDto);
				}
				classesService.deleteClass(existsClasses);
				return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/subject/main";
			} else {
				return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/subject/main";
			}
		} catch (Exception e) {
			return "redirect:/" + userDivn + "/" + pkSubjectSeq + "/subject/main";
		}
	}
	
}