package com.greedy.onoff.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.greedy.onoff.append.entity.Append;

public class FileUploadUtils {
	
	public static String saveFile(String uploadDir, String fileName, MultipartFile singleFile) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		String replaceFileName = fileName + "." + FilenameUtils.getExtension(singleFile.getOriginalFilename());
		
		try(InputStream inputStream = singleFile.getInputStream()){
			Path filePath = uploadPath.resolve(replaceFileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e) {
			throw new IOException("파일 저장에 실패하였습니다. file name : " + fileName);
		}
		
		return replaceFileName;		
	}
	
	public static void deleteFile(String uploadDir, String fileName) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		Path filePath = uploadPath.resolve(fileName);
		try {
			Files.delete(filePath);
		}catch (IOException e) {
			throw new IOException("파일 삭제에 실패하였습니다. file name:" + fileName);
		}
	}


	
	
	

	
	
public static List<String> saveFiles(String uploadDir, String fileName, List<MultipartFile> appendFile) throws IOException {
		
		List<String> saveFiles = new ArrayList<>();
		
		
		for(int i = 0; i< appendFile.size(); i++) {
			
			Path uploadPath = Paths.get(uploadDir);//path 타입의 객체
			
			if(!Files.exists(uploadPath)) {  //파일 경로 존재 확인후 
				Files.createDirectories(uploadPath); //존재하지 않을 시 파일경로 생성
			}
                                               // .(확장자) png 값이 돌아옴
			String replaceFileName = fileName ="." + FilenameUtils.getExtension(appendFile.get(i).getOriginalFilename());
			            //저장하려는 파일명
			
			//stream을 이용해서 저장
			try(InputStream inputStream = appendFile.get(i).getInputStream()) {
				Path filePath = uploadPath.resolve(replaceFileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("파일을 저장하지 못하였습니다. file name : " + fileName);
			}
			
			saveFiles.add(replaceFileName);
	}
		
		return saveFiles;
	
		
	}
	

}
