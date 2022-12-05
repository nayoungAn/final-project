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

}

