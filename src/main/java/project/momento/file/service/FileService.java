package project.momento.file.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import project.momento.file.dto.FileDto;
import project.momento.file.mapper.FileMapper;

@Service
public class FileService {
	
	@Autowired
	private FileMapper fileMapper;

	public int uploadFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir="/file";
		
		String filePath;

		if (isWindows()) {
		    filePath = "C:" + File.separator + "file" + File.separator + fileName;
		} else {
		    filePath = File.separator + "file" + File.separator + fileName;
		}

		Path uploadPath = Path.of(uploadDir);

		// 업로드 디렉토리가 존재하지 않을 경우 생성
		if (!Files.exists(uploadPath)) {
		    Files.createDirectories(uploadPath);
		}

        // 파일 저장 경로
		Path targetLocation = uploadPath.resolve(filePath);

        // 파일 업로드
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        
		// 파일 확장자 추출
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

		// 이미지의 가로 및 세로 크기 추출
		int width = 0;
		int height = 0;
		if (extension != null && extension.toLowerCase().matches("png|jpg|jpeg|gif|bmp")) {
		    BufferedImage image = ImageIO.read(file.getInputStream());
		    width = image.getWidth();
		    height = image.getHeight();
		}
		
        // 파일 정보 저장
		//LoginDto loginDto = (LoginDto) request.getSession().getAttribute("loginDto");
		
        FileDto fileDto = new FileDto();
        fileDto.setFileNm(file.getOriginalFilename());
        fileDto.setFileSize(file.getSize());
        fileDto.setFilePath(filePath);
        fileDto.setFileOriginalPath(file.getOriginalFilename());
        fileDto.setFileExtension(extension);
        fileDto.setFileThumHeiSize(height);
        fileDto.setFileThumWidSize(width);
        
        fileMapper.insertFile(fileDto);
        return fileDto.getReturnId();
	}
	
	public boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win");
    }
	
	public FileDto selectFile(int pkFileSeq) {
		return fileMapper.selectFile(pkFileSeq);
	}

	public void deleteFile(FileDto fileDto) {
		String fileName = fileDto.getFileNm();
	    String filePath;

	    // 파일 경로 생성
	    if (isWindows()) {
		    filePath = "C:" + File.separator + "file" + File.separator + fileName;
		} else {
		    filePath = File.separator + "file" + File.separator + fileName;
		}
		
		File file = new File(filePath);
		if (file.exists()) {
			if (file.delete()) {
				System.out.println(fileName + " 삭제 성공");
			} else {
				System.out.println(fileName + " 삭제 실패");
			}
		}
		fileMapper.deleteFile(fileDto);
	}
	
}
