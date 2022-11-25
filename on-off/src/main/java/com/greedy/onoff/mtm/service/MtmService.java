package com.greedy.onoff.mtm.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.OpenClassesDto;

import com.greedy.onoff.member.entity.Member;
import com.greedy.onoff.mtm.dto.MtmDto;
import com.greedy.onoff.mtm.entity.Mtm;
import com.greedy.onoff.mtm.repository.MtmRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MtmService {
	
	private final ModelMapper modelMapper;
	private final MtmRepository mtmRepository;
	
	
	public MtmService(ModelMapper modelMapper, MtmRepository mtmRepository) {
		this.modelMapper = modelMapper;
		this.mtmRepository = mtmRepository;
	}

	//상담내역 조회
		public Page<MtmDto> selectMtmList(int page, Long classCode) {
			
			
			Pageable pageable = PageRequest.of(page -1, 10, Sort.by("mtmRefer").descending());
			
			Page<Mtm> mtmList = mtmRepository.findByClassCode(pageable, classCode);
					
			Page<MtmDto> mtmDtoList = mtmList.map(mtm -> modelMapper.map(mtm, MtmDto.class));
			
			return mtmDtoList;
		}
	
	@Transactional
	public Mtm insertQnaReply(MtmDto mtmDto) {
		
		
		
		if(mtmDto.getMtmCode() != null) {
			Mtm origin = mtmRepository.findById(mtmDto.getMtmCode()).orElseThrow();
			origin.setAnswerCode(origin.getAnswerCode() +1);
			mtmDto.setMtmCode(null);
			mtmDto.setMtmRefer(origin.getMtmRefer());
		}
		
			
		return mtmRepository.save(modelMapper.map(mtmDto, Mtm.class));
	}

	
	public MtmDto selectMtmDetail(Long mtmCode) {
		
		MtmDto mtmDto = modelMapper.map(mtmRepository.findById(mtmCode)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다.")), MtmDto.class);
		
		return mtmDto;
	}

	
	@Transactional
	public MtmDto updateQnaReply(MtmDto mtmDto) {
		
		Mtm foundMtm = mtmRepository.findById(mtmDto.getMtmCode())
				.orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
		
		foundMtm.update(mtmDto.getMtmTitle(), mtmDto.getMtmDescription());
		
		mtmRepository.save(foundMtm);
		
		return mtmDto;
	}

	public MtmDto deleteQnaReply(Long mtmCode) {
		
		Mtm mtm = mtmRepository.findByMtmCode(mtmCode);
		mtm.setMtmDelete("Y");
		mtmRepository.save(mtm);		
		
		return modelMapper.map(mtm, MtmDto.class);
	}



	



	
	
	
	

}
