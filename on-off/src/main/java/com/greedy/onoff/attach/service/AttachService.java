package com.greedy.onoff.attach.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		
		
		
		Pageable pageable = PageRequest.of(page - 1, 8, Sort.by("classCode").descending());
		
		Page<OpenClasses> classesList = myclassRepository.findByMember(pageable, modelMapper.map(teacher, Member.class));
		
		Page<OpenClassesDto> classesDtoList = classesList.map(openclasses -> modelMapper.map(openclasses, OpenClassesDto.class));
		
		
		
		return classesDtoList;
	}
	
	
	
	/*2. 내강의 정보 상세 조회 */

	public Map<String, Object> selectMyclass(Long classCode) {
		
		log.info("[AttachService] selectMyclass Start==============================");
		log.info("[AttachService] classCode : {}", classCode);
		
		OpenClasses openClasses = (myclassRepository.findByclassCode(classCode))
				.orElseThrow(()-> new IllegalArgumentException("해당 강좌가 없습니다. classCode =" + classCode));
		OpenClassesDto openclassesDto = modelMapper.map(openClasses, OpenClassesDto.class);
		
		List<AttachDto> attachList = attachRepository.findByOpenClasses(classCode).stream().map(at -> modelMapper.map(at, AttachDto.class)).toList();
		
		Map<String, Object> detail = new HashMap<>();
		detail.put("openClasses", openclassesDto);
		detail.put("attachList", attachList);
	
		log.info("[AttachService] openclassesDto: " + openclassesDto);
		
		log.info("[AttachService] selectMyclass End==============================");
		
		
		return detail;
	}

	
	/*3. 강의 자료 공유 등록 */
	
	@Transactional //트랜젝션 단위로 묶음  //AttachDto
	public AttachDto attachRegist(AttachDto attach) {
		
		log.info("[AttachService] 첨부파일 등록 ===========================");
		log.info("[AttachServcie] attachDto:{}", attach);
		
		
		
		//첨부파일
		String fileName = UUID.randomUUID().toString().replace("-",""); //랜덤 유효 아이디 생성
		List<String> replaceFilesName = null;
		List<AppendDto> appendList = new ArrayList<AppendDto>();
		
		AppendDto appendDto = null; 
		
		String date = LocalDate.now().toString();
		attach.setAttachDate(date);

		
		try {
			
			replaceFilesName = FileUploadUtils.saveFiles(FILE_DIR, fileName, attach.getAppendFile());
			
			
			//파일을 배열로 관리
			for(String replaceFileName : replaceFilesName) {
				
				appendDto = new AppendDto();
				
				appendDto.setAppendSaveFile(replaceFileName);
				
				appendDto.setAppendPath(fileName);//파일네임 전달
				
				
				
				appendList.add(appendDto);
				
			}
			
			attach.setAppendList(appendList);
			
			//강의자료 저장
			attachRepository.save(modelMapper.map(attach, Attach.class));
	
			
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		

		log.info("[AttachService] 첨부파일 등록 종료  ==========================");

		return attach;
	}
	


	
	/*4. 강의자료 조회*/


//	public AttachDto findByattachSearch(OpenClassesDto classCode) {
//		
//		log.info("[attachSearch] attachSearchList Start=====================================");
//		
//		log.info("[attachSearch] openclassesDto:{}", classCode  );
//		
//		OpenClassesDto attachSearchList = null;
//		
//		attachSearchList = attachRepository.findByattachSearchList(classCode.getClassCode());
//		
////		OpenClasses openClasses = (myclassRepository.findByclassCode(classCode))
////				.orElseThrow(()-> new IllegalArgumentException("해당 강좌가 없습니다. classCode =" + classCode));
////		OpenClassesDto openclassesDto = modelMapper.map(openClasses, OpenClassesDto.class);
//		
//		
//		
////		AttachDto attachList = attachSearchList.stream().map(attach -> modelMapper.map(attach,AttachDto.class ))
////			.collect(Collectors.toList());
//		log.info("[attachSearch] attachSearhList End========================================");	
//		
//		return attachSearchList;
//	}
	
	
//	
//	/*현재 작업중*/
//	public attachDto findByattachSearch(Long classCode) {
//		
//		log.info("[attachSearch] attachSearchList Start=====================================");
//		log.info("[attachSearch] openclassesDto:{}", classCode  );
//		
//		//OpenClassesDto attachSearchList = null;
//		
//		OpenClasses attachSearchList = (attachRepository.findByattachSearch(classCode))
//				.orElseThrow(()-> new IllegalArgumentException("강의자료가 없습니다. classCode =" + classCode));
//		OpenClassesDto attachSearch = modelMapper.map(attachSearchList, OpendClassesDto.class); 
//		
//		
//		
//		return attachSearchList;
//	}


	
	/*7. 원생정보 조회 */


	public Page<MemberDto> findBystudentList(int page, String memberName) {
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("memberCode").descending());
		
		Page<Member> memberList = studentinfoRepository.findByMemberNameContains(pageable, memberName);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		
		
		
		return memberDtoList;
	}













}
