package com.greedy.onoff.member.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.repository.HistoryRepository;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.DuplicateMemberEmailException;
import com.greedy.onoff.member.exception.FindMemberFaildeException;
import com.greedy.onoff.member.repository.StudentManagerRepository;
import com.greedy.onoff.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentManagerService {
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	@Value("${image.image-url}")
	private String IMAGE_URL;
	
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final StudentManagerRepository studentManagerRepository;
	private final HistoryRepository historyRepository;
	
	public StudentManagerService(ModelMapper modelMapper,  PasswordEncoder passwordEncoder, StudentManagerRepository studentManagerRepository, HistoryRepository historyRepository) {
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.studentManagerRepository = studentManagerRepository;
		this.historyRepository = historyRepository;
		
	}
	
	/* 원생 조회 */
	public Page<MemberDto> selectStudentList(int page) {
		log.info("[StudentService] selectStudentList Start ============================");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("memberCode").ascending());
		
		Page<Member> studentList = studentManagerRepository.findByMemberRole(pageable, "ROLE_STUDENT");
		
		Page<MemberDto> studentDtoList = studentList.map(student -> modelMapper.map(student, MemberDto.class));
		
		log.info("[StudentService] studentList : {}", studentList.getContent());
		log.info("[StudentService] selectStudentList End ============================");
		
		return studentDtoList;
	}

	
	/* 원생 상세 조회 */
	
	  public MemberDto selectStudent(Long memberCode) {
	  log.info("[StudentService] selectStudent Start ============================"); 
	  log.info("[StudentService] memberCode : {}", memberCode);
	  
	  MemberDto memberDto = modelMapper.map(studentManagerRepository.findById(memberCode)
	  .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다.")), MemberDto.class);
	  
	  log.info("[StudentService] selectStudent End ============================");
	  
	  return memberDto;
	  
	  }
	  
	  
	  /* 듣고있는 강의 목록 조회 */
		public List<ClassesHistoryDto> studentClassList(Long memberCode, MemberDto memberDto) {
			log.info("[StudentService] studentClassList Start ============================");
			log.info(memberDto.getMemberName());
			List<ClassesHistory> classesHistoryList = historyRepository.findByMember(modelMapper.map(memberDto, Member.class));
			
			log.info("[StudentService] studentClassList End ============================");	
			
			return classesHistoryList.stream().map(classes -> modelMapper.map(classes, ClassesHistoryDto.class))
					.collect(Collectors.toList());
		}
	 
	
	/* 원생 이름 검색 */
	public Page<MemberDto> selectStudentName(int page, String memberName) {
		
		log.info("[memberList] selectStudentName Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());
		
		Page<Member> memberList = studentManagerRepository.findByMemberNameContains(pageable, memberName);
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
		memberDtoList.forEach(product -> product.setMemberImageUrl(IMAGE_URL + product.getMemberImageUrl()));
		
		log.info("[memberList] memberDtoList : {}", memberDtoList.getContent());
		
		log.info("[memberList] selectStudentName End =====================" );
		
		return memberDtoList;
	}

	/* 원생 정보 수정 */
	@Transactional
	public Object updateStudent(MemberDto memberDto) {
		
		log.info("[StudentService] updateStudent Start ============================");
		log.info("[StudentService] memberDto : {}" + memberDto);
		
		String replaceFileName = null;
		
		Member oriStudent = studentManagerRepository.findById(memberDto.getMemberCode())
				.orElseThrow(() -> new FindMemberFaildeException("등록되지 않은 원생입니다." + memberDto.getMemberCode()));
		String oriImage = oriStudent.getMemberImageUrl();
		try {
			
			if (memberDto.getMemberImage() != null) {
				
				/* 새로 입력 된 이미지 저장 */
				String imageName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, memberDto.getMemberImage());
				memberDto.setMemberImageUrl(replaceFileName);
				
				/* 기존에 저장 된 이미지 삭제*/
				FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);

			} else { 
				/* 이미지를 변경하지 않는 경우 */
				memberDto.setMemberImageUrl(oriImage);
			}
			
			oriStudent.studentUpdate(
					memberDto.getMemberId(), 
					memberDto.getMemberName(), 
					memberDto.getMemberPhone(),  
					memberDto.getMemberAddress(), 
					memberDto.getMemberStatus(),
					memberDto.getMemberEmail(), 
					memberDto.getMemberRole(),
					memberDto.getMemberImageUrl());
			
			studentManagerRepository.save(oriStudent);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		log.info("[StudentService] updateStudent End ============================");
		
		return memberDto;
	}

	/* 원생 등록 */
	@Transactional
	public Object signupStudent(MemberDto memberDto) {
		
		log.info("[StudentService] signupStudent Start ============================");
		log.info("[StudentService] memberDto : {}" + memberDto);
		
		if(studentManagerRepository.findByMemberId(memberDto.getMemberId()) != null) {
			log.info("[StudentService] 아이디가 중복 됩니다.");
			throw new DuplicateMemberEmailException("아이디가 중복 됩니다.");
		}
		
		if(studentManagerRepository.findByMemberEmail(memberDto.getMemberEmail()) != null) {
			log.info("[StudentService] 이메일이 중복 됩니다.");
			throw new DuplicateMemberEmailException("이메일이 중복 됩니다.");
		}
		
		
		memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
		memberDto.setMemberRole("ROLE_STUDENT");
		studentManagerRepository.save(modelMapper.map(memberDto, Member.class));
		
		
		
		
		log.info("[StudentService] signupStudent End ============================");
		return memberDto;
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
