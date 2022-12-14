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
	
	/* ์์ ์กฐํ */
	public Page<MemberDto> selectStudentList(int page) {
		log.info("[StudentService] selectStudentList Start ============================");
		
		Pageable pageable = PageRequest.of(page -1, 7, Sort.by("memberCode").ascending());
		
		Page<Member> studentList = studentManagerRepository.findByMemberRole(pageable, "ROLE_STUDENT");
		
		Page<MemberDto> studentDtoList = studentList.map(student -> modelMapper.map(student, MemberDto.class));
		
		log.info("[StudentService] studentList : {}", studentList.getContent());
		log.info("[StudentService] selectStudentList End ============================");
		
		return studentDtoList;
	}

	
	/* ์์ ์์ธ ์กฐํ */
	
	  public MemberDto selectStudent(Long memberCode) {
	  log.info("[StudentService] selectStudent Start ============================"); 
	  log.info("[StudentService] memberCode : {}", memberCode);
	  
	  MemberDto memberDto = modelMapper.map(studentManagerRepository.findById(memberCode)
	  .orElseThrow(() -> new RuntimeException("์กด์ฌํ์ง ์๋ ํ์์๋๋ค.")), MemberDto.class);
	  
	  memberDto.setMemberImageUrl(IMAGE_URL + memberDto.getMemberImageUrl());
	  log.info("[StudentService] selectStudent End ============================");
	  
	  return memberDto;
	  
	  }
	  
	  
	  /* ๋ฃ๊ณ?์๋ ๊ฐ์ ๋ชฉ๋ก ์กฐํ */
		public List<ClassesHistoryDto> studentClassList(Long memberCode, MemberDto memberDto) {
			log.info("[StudentService] studentClassList Start ============================");
			log.info(memberDto.getMemberName());
			List<ClassesHistory> classesHistoryList = historyRepository.findByMember(modelMapper.map(memberDto, Member.class));
			
			log.info(classesHistoryList.toString());
			log.info("[StudentService] studentClassList End ============================");	
			
			return classesHistoryList.stream().map(classes -> modelMapper.map(classes, ClassesHistoryDto.class))
					.collect(Collectors.toList());
		}
	 
	
	/* ์์ ์ด๋ฆ ๊ฒ์ */
	public Page<MemberDto> selectStudentName(int page, String memberName) {
		
		log.info("[memberList] selectStudentName Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());
		
		Page<Member> memberList = studentManagerRepository.findByMemberNameContainsAndMemberRole(pageable, memberName, "ROLE_STUDENT");		
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		/* ํด๋ผ์ด์ธํธ ์ธก์์ ์๋ฒ์ ์?์ฅ ๋ ์ด๋ฏธ์ง ์์ฒญ ์ ํ์ํ ์ฃผ์๋ก ๊ฐ๊ณต */
		memberDtoList.forEach(student -> student.setMemberImageUrl(IMAGE_URL + student.getMemberImageUrl()));
		
		log.info("[memberList] memberDtoList : {}", memberDtoList.getContent());
		
		log.info("[memberList] selectStudentName End =====================" );
		
		return memberDtoList;
	}

	/* ์์ ์?๋ณด ์์? */
	@Transactional
	public Object updateStudent(MemberDto memberDto) {
		
		log.info("[StudentService] updateStudent Start ============================");
		log.info("[StudentService] memberDto : {}" + memberDto);
		
		String replaceFileName = null;
		
		Member oriStudent = studentManagerRepository.findById(memberDto.getMemberCode())
				.orElseThrow(() -> new FindMemberFaildeException("๋ฑ๋ก๋์ง ์์ ์์์๋๋ค." + memberDto.getMemberCode()));
		String oriImage = oriStudent.getMemberImageUrl();
		try {
			
			if (memberDto.getMemberImage() != null) {
				
				/* ์๋ก ์๋?ฅ ๋ ์ด๋ฏธ์ง ์?์ฅ */
				String imageName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, memberDto.getMemberImage());
				memberDto.setMemberImageUrl(replaceFileName);
				
				if(oriImage != null) {
					/* ๊ธฐ์กด์ ์?์ฅ ๋ ์ด๋ฏธ์ง ์ญ์?*/
					FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
				}

			} else { 
				/* ์ด๋ฏธ์ง๋ฅผ ๋ณ๊ฒฝํ์ง ์๋ ๊ฒฝ์ฐ */
				memberDto.setMemberImageUrl(oriImage);
			}
			
			oriStudent.studentUpdate(
					memberDto.getMemberId(), 
					memberDto.getMemberName(), 
					memberDto.getMemberBirthday(),
					memberDto.getMemberGender(),
					memberDto.getMemberEmail(), 
					memberDto.getMemberPhone(),  
					memberDto.getMemberAddress(), 
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

	/* ์์ ๋ฑ๋ก */
	@Transactional
	public Object signupStudent(MemberDto memberDto) {
		
		log.info("[StudentService] signupStudent Start ============================");
		log.info("[StudentService] memberDto : {}" + memberDto);
		String replaceFileName = null;
		
		if(studentManagerRepository.findByMemberId(memberDto.getMemberId()) != null) {
			log.info("[StudentService] ์์ด๋๊ฐ ์ค๋ณต ๋ฉ๋๋ค.");
			throw new DuplicateMemberEmailException("์์ด๋๊ฐ ์ค๋ณต ๋ฉ๋๋ค.");
		}
		
		if(studentManagerRepository.findByMemberEmail(memberDto.getMemberEmail()) != null) {
			log.info("[StudentService] ์ด๋ฉ์ผ์ด ์ค๋ณต ๋ฉ๋๋ค.");
			throw new DuplicateMemberEmailException("์ด๋ฉ์ผ์ด ์ค๋ณต ๋ฉ๋๋ค.");
		}
		
		try {
			
			if(memberDto.getMemberImage() != null) {
				String imageName = UUID.randomUUID().toString().replace("-", "");
				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, memberDto.getMemberImage());
				memberDto.setMemberImageUrl(replaceFileName);
			}
			
			log.info("[StudentService] replaceFileName : {}", replaceFileName);
			
			
			memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
			memberDto.setMemberRole("ROLE_STUDENT");
			studentManagerRepository.save(modelMapper.map(memberDto, Member.class));
	
		} catch (IOException e) {
			e.printStackTrace();
			 try {
				 FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	 
		}
		
		log.info("[StudentService] signupStudent End ============================");
		return memberDto;
	}
	
	/*  ์์ ๋ชฉ๋ก ์กฐํ - ์ํ 'n' ํฌํจ , ํ์ด์ง X (๊ด๋ฆฌ์) */
	public List<MemberDto> selectStudentListForAdmin() {
		

		List<Member> memberList = studentManagerRepository.findByMemberRole("ROLE_STUDENT");
		List<MemberDto> memberDtoList = memberList.stream()
		.map(member -> modelMapper.map(member, MemberDto.class))
		.collect(Collectors.toList());
		
		
		log.info("[StudentService] memberDtoList End =====================" );
		
		return memberDtoList;
	}


	
	/* ์์ ์์ธ ์กฐํ - memberCode๋ก ๊ฐ์ฌ 1๋ช ์กฐํ , ํฌํจ์ํ 'N' ํฌํจ (๊ด๋ฆฌ์) */
	public MemberDto selectStudentDetail(Long memberCode) { 
        log.info("[StudentService] selectStudentDetail Start ===================================");
        log.info("[StudentService] memberCode : " + memberCode);
        
        Member member = studentManagerRepository.findById(memberCode)
        		.orElseThrow(() -> new IllegalArgumentException("ํด๋น ์์์ด ์์ต๋๋ค. memberCode=" + memberCode));
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        
        log.info("[StudentService] memberDto : " + memberDto);
        log.info("[StudentService] selectStudentDetail End ===================================");
        
        return memberDto;
	}
	

	/* ์์ด๋ ์ค๋ณต ์ฒดํฌ */
	public Boolean checkMemberIdDuplicate(String memberId) {
		log.info("[StudentService] checkMemberIdDuplicate Start ============================");

		return studentManagerRepository.existsByMemberId(memberId);
	}

	/* ์ด๋ฉ์ผ ์ค๋ณต ํ์ธ */
	public Boolean checkMemberEmailDuplicate(String memberEmail) {
		return studentManagerRepository.existsByMemberEmail(memberEmail);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
