package com.greedy.onoff.attach.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.append.dto.AppendDto;
import com.greedy.onoff.attach.dto.AttachDto;
import com.greedy.onoff.attach.entity.Attach;
import com.greedy.onoff.attach.repository.AttachRepository;
import com.greedy.onoff.attach.repository.MyClassRepository;
import com.greedy.onoff.attach.repository.StudentInfoRepository;
import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttachService {
	
	private final MyClassRepository myclassRepository; 
	private final StudentInfoRepository studentinfoRepository;
	private final ModelMapper modelMapper;
	private final AttachRepository attachRepository;
	
	@Value("${file.file-dir}"+ "/attachfile")
	private String FILE_DIR;
	
	public AttachService(MyClassRepository myclassRepository, ModelMapper modelMapper, StudentInfoRepository studentinfoRepository ,
			AttachRepository attachRepository) {
		this.myclassRepository = myclassRepository;
		this.modelMapper = modelMapper;
		this.studentinfoRepository = studentinfoRepository;
		this.attachRepository = attachRepository;
		
	}
	
	
     /* 1. 내강의 목록 조회 - 페이징, 내강의 목록(강사)*/


	public Page<OpenClassesDto> findbyClassesforteacher(int page, MemberDto teacher) {
		
		
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classCode").descending());
		
		Page<OpenClasses> classesList = myclassRepository.findByMember(pageable, modelMapper.map(teacher, Member.class));
		
		Page<OpenClassesDto> classesDtoList = classesList.map(openclasses -> modelMapper.map(openclasses, OpenClassesDto.class));
		
		
		
		return classesDtoList;
	}
	
	
	
	/*2. 내강의 정보 상세 조회 */

	public OpenClassesDto selectMyclass(Long classCode) {
		
		log.info("[AttachService] selectMyclass Start==============================");
		log.info("[AttachService] classCode : {}", classCode);
		
		OpenClasses openClasses = (myclassRepository.findByclassCode(classCode))
				.orElseThrow(()-> new IllegalArgumentException("해당 강좌가 없습니다. classCode =" + classCode));
		OpenClassesDto openclassesDto = modelMapper.map(openClasses, OpenClassesDto.class);
		
		log.info("[AttachService] openclassesDto: " + openclassesDto);
		
		log.info("[AttachService] selectMyclass End==============================");
		
		
		return openclassesDto;
	}

	
	/*3. 강의 자료 공유 등록 */
	
	@Transactional //트랜젝션 단위로 묶음  //AttachDto
	public AttachDto attachRegist(AttachDto attachDto) {
		
		log.info("[AttachService] 첨부파일 등록 ===========================");
		log.info("[AttachServcie] attachDto:{}", attachDto);
		
		
		
		//첨부파일
		String fileName = UUID.randomUUID().toString().replace("-",""); //랜덤 유효 아이디 생성
		List<String> replaceFilesName = null;
		List<AppendDto> appendList = new ArrayList<AppendDto>();
		
		AppendDto appendDto = null; //추가
		
		String date = LocalDate.now().toString();
		attachDto.setAttachDate(date);
	  
		//Long classCode = Attach.getOpenClasses().getClassCode();
		
		
		
		try {
			
			
			replaceFilesName = FileUploadUtils.saveFiles(FILE_DIR, fileName, attachDto.getAppendFile());
			
			
			//파일을 배열로 관리
			for(String replaceFileName : replaceFilesName) {//
				
				appendDto = new AppendDto();//
				
				appendDto.setAppendSaveFile(replaceFileName);
				
				appendList.add(appendDto);//
				
			}
			
			attachDto.setAppendList(appendList);
			
			
			attachRepository.save(modelMapper.map(attachDto, Attach.class));
	
			
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		

		log.info("[AttachService] 첨부파일 등록 종료  ==========================");

		return attachDto;
	}
	





	
	/*7. 원생정보 조회 */


	public Page<MemberDto> findBystudentList(int page, String memberName) {
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("memberCode").descending());
		
		Page<Member> memberList = studentinfoRepository.findByMemberNameContains(pageable, memberName);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		
		
		
		return memberDtoList;
	}



}
