package com.greedy.onoff.student.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.entity.ClassesSchedule;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.member.exception.UserNotFoundException;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.mtm.entity.Mtm;
import com.greedy.onoff.re.entity.Re;
import com.greedy.onoff.student.repository.StudentClassesRepository;
import com.greedy.onoff.student.repository.StudentQnaRepository;
import com.greedy.onoff.subject.entity.Subject;
import com.greedy.onoff.teacher.dto.TeacherHistoryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentClassesService {

	private final ModelMapper modelMapper;
	private final StudentClassesRepository studentClassesRepository;
	private final StudentQnaRepository studentQnaRepository;
	
	
	public StudentClassesService(ModelMapper modelMapper, StudentClassesRepository studentClassesRepository, StudentQnaRepository studentQnaRepository) {
		this.modelMapper = modelMapper;
		this.studentClassesRepository = studentClassesRepository;
		this.studentQnaRepository = studentQnaRepository;
	}
	
	  	/* 1. 내강의 목록 조회 - 페이징, 내강의 목록(원생), "수강중"인 강의만 조회 */
		public Page<ClassesHistoryDto> myclassListForMember(int page, MemberDto member) {
		
		
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classHistoryCode").descending());
		
		
		Page<ClassesHistory> classesList = studentClassesRepository.findByMemberAndClassStatus(pageable, modelMapper.map(member, Member.class), "수강중");
		
		log.info("[AttachService] classesList : {}", classesList);
		
		Page<ClassesHistoryDto> classesDtoList = classesList.map(c -> modelMapper.map(c, ClassesHistoryDto.class));
		log.info("[AttachService] classesDtoList : {}", classesDtoList);
		
		
		return classesDtoList;
		
		}
		

		
		/* qna 목록 조회*/
		public Page<MtmDto> selectMtmList(int page, MemberDto member) {
			
			
			//Mtm orginMember = studentQnaRepository.findAll( member )
					//.orElseThrow(() -> new UserNotFoundException(member + "를 찾을 수 없습니다."));
			
			Long memberCode = member.getMemberCode();
			log.info("멤버코드 : {} ", memberCode.toString());
			
			
			Pageable pageable = PageRequest.of(page -1, 10, Sort.by("mtmDelete").descending());
			Page<Mtm> mtmCode = studentQnaRepository.findAll(pageable);
			Page<MtmDto> mtmCodeList = mtmCode.map(mtm -> modelMapper.map(mtm, MtmDto.class));
			log.info("답변테스트 : {} ", mtmCodeList.getContent());
			
			
			Page<Mtm> mtmList = studentQnaRepository.findByMember(pageable, modelMapper.map(member, Member.class));
					
			Page<MtmDto> mtmDtoList = mtmList.map(mtm -> modelMapper.map(mtm, MtmDto.class));
			log.info("상담내역조회 : {} ", mtmDtoList.getContent());
			
			return mtmDtoList;
		}
		
		//1:1상담 등록
		@Transactional
		public Mtm insertQnaRequest(MtmDto mtmDto, MemberDto memberDto) {
		
			
			if(mtmDto.getMtmCode() != null) {
				Mtm origin = studentQnaRepository.findById(mtmDto.getMtmCode()).orElseThrow();
				origin.setAnswerCode(origin.getAnswerCode() +1);
				mtmDto.setMtmCode(null);
				
			}
			
			return studentQnaRepository.save(modelMapper.map(mtmDto, Mtm.class));
		}

		
		//1:1상담 상세 조회
		public MtmDto selectMtmDetail(Long mtmCode) {
			
			Mtm mtm = studentQnaRepository.findById(mtmCode)
						.orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
			
			MtmDto mtmDto = modelMapper.map(mtm, MtmDto.class);
			
			return mtmDto;
		}

		
		//1:1상담 수정
		@Transactional
		public MtmDto updateQnaRequest(MtmDto mtmDto) {
			
			Mtm foundMtm = studentQnaRepository.findById(mtmDto.getMtmCode())
					.orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
			
			foundMtm.update(mtmDto.getMtmTitle(), mtmDto.getMtmDescription(),
					modelMapper.map(mtmDto.getReList(), Re.class),
//					.stream()
//					.map(reList -> modelMapper.map(reList,Re.class))
//					.collect(Collectors.toList()),
					modelMapper.map(mtmDto.getMember(), Member.class), 
					modelMapper.map(mtmDto.getClasses(), OpenClasses.class));
			
			studentQnaRepository.save(foundMtm);
			
			return mtmDto;
		}
		
		//1:1상담 삭제
		public MtmDto deleteQnaRequest(Long mtmCode) {
			
			Mtm mtm = studentQnaRepository.findByMtmCode(mtmCode);
			mtm.setMtmDelete("Y");
			studentQnaRepository.save(mtm);		
			
			return modelMapper.map(mtm, MtmDto.class);
		}

		
		

		
	
	
}
