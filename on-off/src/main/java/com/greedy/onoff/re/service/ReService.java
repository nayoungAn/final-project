package com.greedy.onoff.re.service;


import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.mtm.entity.Mtm;
import com.greedy.onoff.mtm.repository.MtmRepository;
import com.greedy.onoff.re.dto.ReDto;
import com.greedy.onoff.re.entity.Re;
import com.greedy.onoff.re.repository.ReRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReService {
	
	private final ModelMapper modelMapper;
	private final ReRepository reRepository;
	private final MtmRepository mtmRepository;
	
	public ReService(ModelMapper modelMapper, MtmRepository mtmRepository, ReRepository reRepository) {
		this.modelMapper = modelMapper;
		this.reRepository = reRepository;
		this.mtmRepository = mtmRepository;
	}
	
	/* 상담 내역 조회 */
	public Page<MtmDto> selectQnaList(int page, Long classCode) {
		
		
		Pageable pagable = PageRequest.of(page -1, 10, Sort.by("mtmCode").descending());
		
		Page<Mtm> mtmList = mtmRepository.findByClassCode(pagable, classCode);
		 Page<MtmDto> mtmDtoList = mtmList.map(mtm -> modelMapper.map(mtm, MtmDto.class));
		log.info("상담내역조회 : {} ", mtmDtoList);
		
		//List<Mtm> mtmList = mtmRepository.findByClassCode(classCode, (page - 1) * 10, 10);
		//log.info("mtmList : {} ", mtmList);
		return mtmDtoList;
	}

	
	/* 상담 상세 조회 */
	public MtmDto selectQna(Long mtmCode) {
		
		log.info("[ReService] mtmCode : {}", mtmCode);
		
		Mtm mtm = mtmRepository.findById(mtmCode)
						.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
		MtmDto mtmDto = modelMapper.map(mtm, MtmDto.class);
				
		log.info("[ReService] mtmDto : {}", mtmDto);
		return mtmDto;
	}
	
	
	/* 상담 답글 작성 */
	@Transactional
	public ReDto insertQnaReply(ReDto mtmReDto) {
		
		Mtm origin = mtmRepository.findById(mtmReDto.getMtmCode()).orElseThrow();
		origin.setAnswerCode(origin.getAnswerCode() +1 );		
		mtmReDto.setReStatus("N");
		mtmReDto.setReCode(mtmReDto.getMtmCode());
		reRepository.save(modelMapper.map(mtmReDto, Re.class));
		
		return mtmReDto;
	}
	
	/* 상담 답글 수정*/
	@Transactional
	public ReDto updateQnaReply(ReDto mtmReDto) {
		
		Re foundQnaReply = reRepository.findById(mtmReDto.getReCode())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 답글입니다."));
		
		foundQnaReply.update(mtmReDto.getReTitle(), mtmReDto.getReContent());
		
		reRepository.save(foundQnaReply);
		
		return mtmReDto;
		
	}
	
	/* 상담 답글 삭제*/
	public ReDto deleteQnaReply(Long reCode) {
		
		Re mtmRe = reRepository.findById(reCode)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 답글입니다."));
		
		Mtm origin = mtmRepository.findById(reCode).orElseThrow();
		origin.setAnswerCode(origin.getAnswerCode() -1 );	
		
		mtmRe.setReStatus("Y");
		reRepository.save(mtmRe);
		
		return modelMapper.map(mtmRe, ReDto.class);
	}

	
	/* 상담 답글 상세 조회*/
	public ReDto selectQnaRe(Long reCode) {
		Re re = reRepository.findByReCode(reCode)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
		ReDto reDto = modelMapper.map(re, ReDto.class);
		
		log.info("[ReService] mtmDto : {}", reDto);
		return reDto;
	}
	


}
