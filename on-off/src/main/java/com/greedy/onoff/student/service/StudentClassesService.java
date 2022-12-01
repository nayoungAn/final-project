package com.greedy.onoff.student.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.entity.ClassesHistory;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.mtm.entity.Mtm;
import com.greedy.onoff.re.dto.ReDto;
import com.greedy.onoff.re.entity.Re;
import com.greedy.onoff.student.repository.StudentClassesDetailRepository;
import com.greedy.onoff.student.repository.StudentClassesRepository;
import com.greedy.onoff.student.repository.StudentQnaRepository;
import com.greedy.onoff.student.repository.StudentReRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentClassesService {

	private final ModelMapper modelMapper;
	private final StudentClassesRepository studentClassesRepository;
	private final StudentQnaRepository studentQnaRepository;
	private final StudentClassesDetailRepository studentClassesDetailRepository;
	private final StudentReRepository studentReRepository;
	
	
	public StudentClassesService(ModelMapper modelMapper, StudentClassesRepository studentClassesRepository, StudentQnaRepository studentQnaRepository, StudentClassesDetailRepository studentClassesDetailRepository,
			StudentReRepository studentReRepository) {
		this.modelMapper = modelMapper;
		this.studentClassesRepository = studentClassesRepository;
		this.studentQnaRepository = studentQnaRepository;
		this.studentClassesDetailRepository = studentClassesDetailRepository;
		this.studentReRepository = studentReRepository;
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
		
		/* 2. 내강의 상세목록 조회 */
		public OpenClassesDto selectMyclass(Long classCode) {
			
			log.info("[AttachService] selectMyclass Start==============================");
			log.info("[AttachService] classCode : {}", classCode);
			
			OpenClasses openClasses = (studentClassesDetailRepository.findByClassCode(classCode))
					.orElseThrow(()-> new IllegalArgumentException("해당 강좌가 없습니다. classCode =" + classCode));
			OpenClassesDto openclassesDto = modelMapper.map(openClasses, OpenClassesDto.class);
			
			log.info("[AttachService] openclassesDto: " + openclassesDto);
			
			log.info("[AttachService] selectMyclass End==============================");
			
			
			return openclassesDto;
		}
		
		/* 3. 내강의 리스트 조회 - 노페이징 */
		public List<ClassesHistoryDto> selectClassHistoryListNopaging(MemberDto member) {
			
			
			List<ClassesHistory> classesHistoryList = studentClassesRepository.findByMemberMemberCodeAndClassStatus(member.getMemberCode(), "수강중");
			
			log.info("[AttachService] classesList : {}", classesHistoryList);
			
			List<ClassesHistoryDto> classesHistoryDtoList = classesHistoryList.stream()
					.map(c -> modelMapper.map(c, ClassesHistoryDto.class)).collect(Collectors.toList());
			log.info("[AttachService] classesDtoList : {}", classesHistoryDtoList);
			
			return classesHistoryDtoList;
		}
		

		
		/* 4. 1:1상담 목록 조회*/
		public Page<MtmDto> selectMtmList(int page, MemberDto member) {
			
			
			//Mtm orginMember = studentQnaRepository.findAll( member )
					//.orElseThrow(() -> new UserNotFoundException(member + "를 찾을 수 없습니다."));
			
			Long memberCode = member.getMemberCode();
			log.info("멤버코드 : {} ", memberCode.toString());
			
			
			Pageable pageable = PageRequest.of(page -1, 10, Sort.by("mtmDelete").descending());
			Page<Mtm> mtmCode = studentQnaRepository.findAll(pageable);
			Page<MtmDto> mtmCodeList = mtmCode.map(mtm -> modelMapper.map(mtm, MtmDto.class));
			log.info("답변테스트 : {} ", mtmCodeList.getContent());
			
			
			Page<Mtm> mtmList = studentQnaRepository.findByMemberAndMtmDelete(pageable, modelMapper.map(member, Member.class),"N");
					
			Page<MtmDto> mtmDtoList = mtmList.map(mtm -> modelMapper.map(mtm, MtmDto.class));
			log.info("상담내역조회 : {} ", mtmDtoList.getContent());
			
			return mtmDtoList;
		}
		
		//1:1상담 상세 조회
		public MtmDto selectMtmDetail(Long mtmCode) {
			
			Mtm mtm = studentQnaRepository.findById(mtmCode)
						.orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다."));
			
			MtmDto mtmDto = modelMapper.map(mtm, MtmDto.class);
			
			return mtmDto;
		}
		
		//1:1상담 등록
		@Transactional
		public MtmDto insertQnaRequest(MtmDto mtmDto, MemberDto memberDto) {
		
			log.info("[StudentClassesService] insertQnaRequest Start ===================================");
			log.info("[StudentClassesService] insertQnaRequest : {}", mtmDto);
			
			studentQnaRepository.save(modelMapper.map(mtmDto, Mtm.class));
			
			log.info("[StudentClassesService] insertQnaRequest End ===================================");
			
			return mtmDto;
		}

		
		//1:1상담 수정
		@Transactional
		public MtmDto updateQnaRequest(MtmDto mtmDto) {
			
			log.info("[StudentClassesService] updateQnaRequest Start ===================================");
			log.info("[StudentClassesService] updateQnaRequest : {}", mtmDto);
			
			Mtm foundMtm = studentQnaRepository.findById(mtmDto.getMtmCode())
					.orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
			log.info("[StudentClassesService] Re : {}", mtmDto.getMtmCode());
		
			
			foundMtm.update(
					//mtmDto.getMtmCode(), 
					mtmDto.getMtmDate(), 
					mtmDto.getMtmTitle(), 
					mtmDto.getMtmDescription(), 
					mtmDto.getAnswerCode(),
					mtmDto.getMtmDelete()
					//modelMapper.map(mtmDto.getMember(), Member.class), 
					//modelMapper.map(mtmDto.getClasses(), OpenClasses.class)
					);
			
			studentQnaRepository.save(foundMtm);
			
			
			
			//Re reList = studentReRepository.findByMtmMtmCode(null);
			//log.info("[StudentClassesService] Re : {}", reList);
			
			//studentReRepository.deleteById(reList.getMtmCode());
		
			log.info("[StudentClassesService] updateQnaRequest End ===================================");
			
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
