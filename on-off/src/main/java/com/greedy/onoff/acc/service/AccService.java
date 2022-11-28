package com.greedy.onoff.acc.service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.acc.dto.AccDto;
import com.greedy.onoff.acc.entity.Acc;
import com.greedy.onoff.acc.repository.AccRepository;
import com.greedy.onoff.classes.entity.ClassesHistory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccService {
	
	private final AccRepository accRepository;
	private final ModelMapper modelMapper;
	
	public AccService(AccRepository accRepository,
			ModelMapper modelMapper) {
		this.accRepository = accRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 수납 내역 조회 */
	public Page<AccDto> selectAccListForAdmin(int page) {
		
		log.info("[AccService] selectAccListForAdmin Start ====================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("accCode").descending());
		Page<Acc> accList = accRepository.findAll(pageable);
		Page<AccDto> accDtoList = accList.map(acc -> modelMapper.map(acc, AccDto.class));
		
		log.info("[AccService] accDtoList : {}", accDtoList.getContent());
		
		log.info("[AccService] selectAccListForAdmin End ====================");
		
		return accDtoList;
	}

	/* 수납 내역 상세 조회 */
	public Object selectAccForAdmin(Long accCode) {

		log.info("[AccService] selectAccForAdmin Start ====================");
		log.info("[AccService] accCode " + accCode);
		
		Acc acc = accRepository.findById(accCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 내역이 없습니다!. accCode=" + accCode));
		AccDto accDto = modelMapper.map(acc, AccDto.class);
		
		
		log.info("[AccService] accDto : " + accDto);
		log.info("[AccService] selectAccForAdmin End ====================");
		
		return accDto;
	}

	/* 수납 내역 등록 */
	@Transactional
	public Object insertAcc(AccDto accDto) {
		
		return accRepository.save(modelMapper.map(accDto, Acc.class));
	}

	/* 수납 내역 수정 */
	@Transactional
	public Object updateAcc(AccDto accDto) {
		
		Acc oriAcc = accRepository.findById(accDto.getAccCode()).orElseThrow(
				() -> new IllegalArgumentException("해당 수납 내역이 없습니다. accCode=" + accDto.getAccCode()));
		
		oriAcc.update(
				accDto.getAccDate(),
				accDto.getAccOption(),
				accDto.getAccContent());
				//modelMapper.map(accDto.getClassesHistory(), ClassesHistory.class));
		
		accRepository.save(oriAcc);
		
		return accDto;
	}

}
