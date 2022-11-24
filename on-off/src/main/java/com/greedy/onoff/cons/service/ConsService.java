package com.greedy.onoff.cons.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.cons.dto.ConsDto;
import com.greedy.onoff.cons.entity.Cons;
import com.greedy.onoff.cons.repository.ConsRepository;
import com.greedy.onoff.member.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsService {
	
	private final ConsRepository consRepository;
	private final ModelMapper modelMapper;

	public ConsService(ConsRepository consRepository,ModelMapper modelMapper) {
		this.consRepository = consRepository;
		this.modelMapper = modelMapper;
		
	}
	/* 1. 등록상담 조회 */
	@Transactional
	public ConsDto selectMyInfo(Long consCode) {
		log.info("[ConsService] selectMyInfo Start ===========================");
		log.info("[ConsService] consCode : {}", consCode);
		
		Cons cons = consRepository.findByConsCode(consCode)
				.orElseThrow(() -> new UserNotFoundException(consCode + "를 찾을 수 없습니다."));
		
		log.info("[ConsService] consEntity : {} ", cons);
		
		log.info("[ConsService] signup End ===========================");
		return modelMapper.map(cons,ConsDto.class);
	}

	
	/* 2. 등록상담 목록 조회 - 페이징 (관리자) */
	public Page<ConsDto> selectConsListForAdmin(int page) {
		
		log.info("[ConsService] selectProductListForAdmin Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("ConsCode").descending());
		
		Page<Cons> consList = consRepository.findAll(pageable);
		Page<ConsDto> consDtoList = consList.map(cons -> modelMapper.map(cons, ConsDto.class));
		
		
		log.info("[ConsService] ConsDtoList : {}", consDtoList.getContent());
		
		
		log.info("[ConsService] selectConsListForAdmin End =====================" );
		
		return consDtoList;
	}

	/* 3. 등록상담 등록 */
	@Transactional
	public ConsDto insertCons(ConsDto consDto) throws IOException {
		
		log.info("[ConsService] insertCons Start ===================================");
		log.info("[ConsService] ConsDto : {}", consDto);
		
	
    	
	consRepository.save(modelMapper.map(consDto, Cons.class));
	
	log.info("[ConsService] insertCons End ===================================");
		return consDto;
		
	}
	
	/* 4. 등록상담 수정 */
	@Transactional
	public ConsDto updateCons(ConsDto consDto) throws Exception{

		log.info("[ConsService] updateCons Start ===================================");
		log.info("[ConsService] consDto : {}", consDto);



		Cons oriProduct = consRepository.findById(consDto.getConsCode()).orElseThrow(
					() -> new IllegalArgumentException("해당 상담목록이 없습니다. ConsCode=" + consDto.getConsCode()));


			/* 조회 했던 기존 엔티티의 내용을 수정 */
			oriProduct.updateCons(consDto.getConsCode(), 
					consDto.getConsDate(), 
					consDto.getConsName(), 
					consDto.getConsGender(), 
					consDto.getConsBirth(), 
					consDto.getConsTitle(),
					consDto.getConsDescription(),
					consDto.getConsPhone()
					);
		
			consRepository.save(oriProduct);
			
		log.info("[ConsService] updateCons End ===================================");

		return consDto;
		
	}
	/* 5. 등록상담 삭제 */
	@Transactional
	public ConsDto deleteCons(Long consCode) {
		
		Cons cons = consRepository.findByConsCode(consCode)
				.orElseThrow(() -> new UserNotFoundException(consCode + "를 찾을 수 없습니다."));
		
		log.info("[ConsService] consEntity : {} ", cons);
		
		consRepository.delete(cons);
		
		log.info("[ConsService] deleteCons End ===========================");
		return modelMapper.map(cons,ConsDto.class);	}
	
}
