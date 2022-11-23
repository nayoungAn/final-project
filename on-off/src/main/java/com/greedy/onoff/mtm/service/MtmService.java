/*package com.greedy.onoff.mtm.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
	
	public List<MtmDto> selectMtmList(Long mtmCode) {
		
		log.info("[PurchaseService] selectPurchaseList Start ==============================");
		log.info("[PurchaseService] memberId : {}", mtmCode);
		
		Mtm mtm = mtmRepository.findByMtmCode(mtmCode);
		return null;
	}

}*/
