package com.greedy.onoff.acc.service;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.acc.dto.AccDto;
import com.greedy.onoff.acc.entity.Acc;
import com.greedy.onoff.acc.repository.AccRepository;
import com.greedy.onoff.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccService {
	
	private final AccRepository accRepository;
	private final MemberRepository memberRepository;
	//private final OpenClassesRepository openClassesRepository;
	private final ModelMapper modelMapper;
	
	public AccService(AccRepository accRepository,
			MemberRepository memberRepository, //OpenClassesRepository openClassesRepository,
			ModelMapper modelMapper) {
		this.accRepository = accRepository;
		this.memberRepository = memberRepository;
		//this.openClassesRepository = openClassesRepository;
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

		Acc acc = accRepository.findById(accCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. accCode=" + accCode));
		AccDto accDto = modelMapper.map(acc, AccDto.class);
		
		return accDto;
	}

}
