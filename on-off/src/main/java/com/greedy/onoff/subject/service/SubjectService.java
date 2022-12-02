package com.greedy.onoff.subject.service;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.subject.dto.SubjectDto;
import com.greedy.onoff.subject.entity.Subject;
import com.greedy.onoff.subject.repository.SubjectRepository;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubjectService {

	private final SubjectRepository subjectRepository;
	private final ModelMapper modelMapper;

	public SubjectService(SubjectRepository subjectRepository, ModelMapper modelMapper) {
		this.subjectRepository = subjectRepository;
		this.modelMapper = modelMapper;
	}

	/* 과목 목록 조회 - 페이징, 상태 여부 'N'포함 (관리자) */
	public Page<SubjectDto> selectSubjectListForAdmin(int page) {

		log.info("[SubjectService] selectSubjectList Start =====================");

		Pageable pageable = PageRequest.of(page - 1, 9, Sort.by("subjectCode").descending());

		Page<Subject> subjectList = subjectRepository.findAll(pageable);
		Page<SubjectDto> subjectDtoList = subjectList.map(subject -> modelMapper.map(subject, SubjectDto.class));

		log.info("[SubjectService] subjectDtoList : {}", subjectDtoList.getContent());

		log.info("[SubjectService] subjectDtoList End =====================");

		return subjectDtoList;
	}

	/* 과목 목록 조회 - 상태 여부 'N'포함 (관리자) 노페이징 */
	public List<SubjectDto> selectSubjectListForAdmin() {

		List<Subject> subjectList = subjectRepository.findAll();
		List<SubjectDto> subjectDtoList = subjectList.stream()
				.map(subject -> modelMapper.map(subject, SubjectDto.class)).collect(Collectors.toList());

		log.info("[SubjectService] subjectDtoList End =====================");

		return subjectDtoList;
	}

	/* 2. 과목 목록 조회 - 상품명 검색 기준, 페이징, 상태 여부 'N'포함 (관리자) */
	public Page<SubjectDto> selectSubjectListBySubjectName(int page, String subjectName) {

		log.info("[SubjectService] selectSubjectListBySubjectName Start =====================");

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("subjectCode").descending());

		Page<Subject> subjectList = subjectRepository.findBySubjectNameContains(pageable, subjectName);
		Page<SubjectDto> subjectDtoList = subjectList.map(subject -> modelMapper.map(subject, SubjectDto.class));

		log.info("[ProductService] selectSubjectListBySubjectName End =====================");

		return subjectDtoList;
	}

	/* 3. 과목 상세 조회 - subjectCode로 과목 1개 조회, 상태 여부 'N'포함(관리자) */
	public SubjectDto selectSubjectForAdmin(Long subjectCode) {
		log.info("[SubjectService] selectSubjectForAdmin Start ===================================");
		log.info("[SubjectService] subjectCode : " + subjectCode);

		Subject subject = subjectRepository.findById(subjectCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 과목 없습니다. subjectCode=" + subjectCode));
		SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);

		log.info("[SubjectService] subjectDto : " + subjectDto);

		log.info("[SubjectService] selectSubjectForAdmin End ===================================");

		return subjectDto;
	}

	/* 7. 과목 등록 */
	@Transactional
	public SubjectDto insertSubject(SubjectDto subjectDto) {

		log.info("[SubjectService] insertSubject Start ===================================");
		log.info("[SubjectService] subjectDto : {}", subjectDto);

		subjectRepository.save(modelMapper.map(subjectDto, Subject.class));

		log.info("[SubjectService] insertSubject End ===================================");

		return subjectDto;
	}

	/* 8. 과목 수정 */
	@Transactional
	public SubjectDto updateSubject(SubjectDto subjectDto) {

		log.info("[SubjectService] updateSubject Start ===================================");
		log.info("[SubjectService] subjectDto : {}", subjectDto);

		Subject oriSubject = subjectRepository.findById(subjectDto.getSubjectCode()).orElseThrow(
				() -> new IllegalArgumentException("해당 과목이 없습니다. subjectCode=" + subjectDto.getSubjectCode()));

		/* 조회 했던 기존 엔티티의 내용을 수정 */
		oriSubject.update(subjectDto.getSubjectCode(), subjectDto.getSubjectName(), subjectDto.getSubjectForm(),
				subjectDto.getSubjectLanguage(), subjectDto.getSubjectBook(), subjectDto.getSubjectDescription(),
				subjectDto.getSubjectLearningObjectives());

		subjectRepository.save(oriSubject);

		log.info("[SubjectService] updateSubject End ===================================");

		return subjectDto;
	}

	/* 과목 삭제 */
	@Transactional
	public SubjectDto deleteSubject(Long subjectCode) {

		Subject subject = subjectRepository.findBySubjectCode(subjectCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 과목이 없습니다. subjectCode=" + subjectCode));

		SubjectDto foundSubject = modelMapper.map(subject, SubjectDto.class);

		subjectRepository.delete(subject);
		return foundSubject;
	}

}
