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
	private String IMATE_DIR;
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
			replaceFileName = FileUploadUtils.saveFile(IMATE_DIR, imageName, memberDto.getMemberImage());
			memberDto.setMemberImageUrl(replaceFileName);
			log.info("[TeacherService] replaceFileName : {}", replaceFileName);
			
			
			memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
			memberDto.setMemberRole("ROLE_TEACHER");
			memberRepository.save(modelMapper.map(memberDto, Member.class));
	
		} catch (IOException e) {
			e.printStackTrace();
			 try {
				 FileUploadUtils.deleteFile(IMATE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}	 
		}
		
		
		return memberDto;
	}


	/* 2. 상품 목록 조회 - 페이징, 주문 불가 상품 포함 (관리자) */
	public Page<MemberDto> selectMemberListForAdmin(int page) {
		log.info("[MemberService] selectMemberListForAdmin Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("memberCode").descending());

		Page<Member> memberList = memberRepository.findByMemberRole(pageable,"ROLE_TEACHER");
		Page<MemberDto> memberDtoList = memberList.map(member -> modelMapper.map(member, MemberDto.class));
		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
		memberDtoList.forEach(member -> member.setMemberImageUrl(IMAGE_URL + member.getMemberImageUrl()));
		
		log.info("[MemberService] memberDtoList : {}", memberDtoList.getContent());
		
		log.info("[MemberService] selectMemberListForAdmin End =====================" );
		
		return memberDtoList;
	}


}
