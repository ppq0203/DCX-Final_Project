package project.momento.file.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.momento.file.dto.FileDto;

@Mapper
public interface FileMapper {

	int insertFile(FileDto fileDto);

	FileDto selectFile(int pkFileSeq);

	void deleteFile(FileDto fileDto);

}
