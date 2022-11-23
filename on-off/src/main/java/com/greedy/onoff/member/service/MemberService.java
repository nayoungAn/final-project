package com.greedy.onoff.member.service;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.DuplicateMemberEmailException;
import com.greedy.onoff.member.repository.MemberRepository;
import com.greedy.onoff.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	@Value("${image.image-url}")
	private String IMAGE_URL;
	
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final MemberRepository memberRepository;
	
	public MemberService(ModelMapper modelMapper,  PasswordEncoder passwordEncoder, MemberRepository memberRepository) {
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.memberRepository = memberRepository;
		
	}
	
	
	
	@Transactional
	public MemberDto teacherRegister(MemberDto memberDto) {
		
		log.info("[TeacherService] teacherRegister Start ============================");
		log.info("[TeacherService] memberDto : {}", memberDto);
		String imageName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;
		
		if(memberRepository.findByMemberEmail(memberDto.getMemberEmail()) != null) {
			throw new DuplicateMemberEmailException("이메일이 중복됩니다.");
		}
		
		try {
			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, memberDto.getMemberImage());
			memberDto.setMemberImageUrl(replaceFileName);
			log.info("[TeacherService] replaceFileName : {}", replaceFileName);
			
			
			memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
			memberDto.setMemberRole("ROLE_TEACHER");
			memberRepository.save(modelMapper.map(memberDto, Member.class));
	
		} catch (IOException e) {
			e.printStackTrace();
			 try {
				 FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	 
		}
		
		
		return memberDto;
	}


	/* 1. 강사 목록 조회 - 페이징, 상태 'N' 포함, Role teacher (관리자) */
	public Page<MemberDto> selectTeacherListForAdmin(int page) {
		log.info("[MemberService] selectTeacherListForAdmin Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());

		Page<Member> memberList = memberRepository.findByMemberRole(pageable,"ROLE_TEACHER");
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
		memberDtoList.forEach(member -> member.setMemberImageUrl(IMAGE_URL + member.getMemberImageUrl()));
		
		log.info("[MemberService] memberDtoList : {}", memberDtoList.getContent());
		
		log.info("[MemberService] selectTeacherListForAdmin End =====================" );
		
		return memberDtoList;
	}

	/* 2. 강사목록 조회 - 이름 검색 기준, 페이징, 상태 'N' 포함, Role teacher (관리자) */
	public Page<MemberDto> selectTeacherListByMemberName(int page, String memberName) {
		
		log.info("[MemberService] selectTeacherListByMemberName Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());
		
		Page<Member> memberList = memberRepository.findByMemberNameContainsAndMemberRole(pageable, memberName,"ROLE_TEACHER");
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
		memberDtoList.forEach(member -> member.setMemberImageUrl(IMAGE_URL + member.getMemberImageUrl()));
		
		log.info("[MemberService] memberDtoList : {}", memberDtoList.getContent());
		
		log.info("[MemberService] selectTeacherListByMemberName End =====================" );
		
		return memberDtoList;
	}

	/* 강사 상세 조회 - memberCode로 강사 1명 조회 , 포함상태 'N' 포함 (관리자) */
	public MemberDto selectTeacherForAdmin(Long memberCode) { 
        log.info("[MemberService] selectTeacherForAdmin Start ===================================");
        log.info("[MemberService] memberCode : " + memberCode);
        
        Member member = memberRepository.findById(memberCode)
        		.orElseThrow(() -> new IllegalArgumentException("해당 강사가 없습니다. memberCode=" + memberCode));
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        memberDto.setMemberImageUrl(IMAGE_URL + memberDto.getMemberImageUrl());
        
        log.info("[MemberService] memberDto : " + memberDto);
        
        log.info("[MemberService] selectTeacherForAdmin End ===================================");
        
        return memberDto;
	}



	/* 8. 상품 수정 */
	@Transactional
	public MemberDto updateMember(MemberDto memberDto) {

		log.info("[MemberService] updateMember Start ===================================");
		log.info("[MemberService] memberDto : {}", memberDto);

		String replaceFileName = null;

		try {

			Member oriMember = memberRepository.findById(memberDto.getMemberCode()).orElseThrow(
					() -> new IllegalArgumentException("해당 강사가 없습니다. memberCode=" + memberDto.getMemberCode()));
			String oriImage = oriMember.getMemberImageUrl();

			/* 이미지를 변경하는 경우 */
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
			
			/* 조회 했던 기존 엔티티의 내용을 수정 */
			oriMember.update(memberDto.getMemberName(), 
					memberDto.getMemberPhone(), 
					memberDto.getMemberGender(),
					memberDto.getMemberBirthday(), 
					memberDto.getMemberEmail(), 
					memberDto.getMemberStatus(),
					memberDto.getMemberAddress(),
					memberDto.getMemberImageUrl());
			
			
					memberRepository.save(oriMember);
			
		} catch (IOException e) {
			e.printStackTrace();
			try {
				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		log.info("[MemberService] updateMember End ===================================");

		return memberDto;
	}
	

}
