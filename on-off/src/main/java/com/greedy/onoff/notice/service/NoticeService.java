package com.greedy.onoff.notice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.notice.dto.NoticeDto;
import com.greedy.onoff.notice.entity.Notice;
import com.greedy.onoff.notice.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final ModelMapper modelMapper;
	
	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	public NoticeService(NoticeRepository noticeRepository, ModelMapper modelMapper) {
		this.noticeRepository = noticeRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 1. 공지사항 목록 조회 */
	public Page<NoticeDto> selectNoticeList(int page) {
		
		log.info("[NoticeService] selectNoticeList Start ===================================");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("noticeCode").descending());
		
		Page<Notice> noticeList = noticeRepository.findAll(pageable);
		Page<NoticeDto> noticeDtoList = noticeList.map(notice -> modelMapper.map(notice, NoticeDto.class));
		
		log.info("[NoticeService] NoticeList : {}", noticeDtoList.getContent());
		log.info("[NoticeService] selectNoticeList End ===================================");
		
		return noticeDtoList;
	}
	
	/* 2. 공지사항 상세 조회 */
	public NoticeDto selectNotice(Long noticeCode) {
		
		log.info("[NoticeService] selectNotice Start ===================================");
		log.info("[NoticeService] noticeCode : {}", noticeCode);
		
		Notice notice = noticeRepository.findByNoticeCode(noticeCode)
				.orElseThrow(() -> new IllegalArgumentException("등록된 공지사항이 없습니다. noticeCode=" + noticeCode));
		NoticeDto noticeDto = modelMapper.map(notice, NoticeDto.class);
		
		log.info("[NoticeService] noticeDto : " + noticeDto);
		log.info("[NoticeService] selectNotice End ===================================");
		
		return noticeDto;
	}
	
	/* 3. 공지사항 등록 */
	@Transactional
	public NoticeDto insertNotice(NoticeDto noticeDto) {
		log.info("[NoticeService] insertNotice Start ===================================");
		log.info("[NoticeService] insertNotice noticeDto : {}", noticeDto);
		
		String date = LocalDate.now().toString();
		
		noticeDto.setNoticeDate(date);
		
		noticeRepository.save(modelMapper.map(noticeDto, Notice.class));
		
		log.info("[NoticeService] insertNotice End ===================================");
		
		return noticeDto;
	}

	/* 4. 공지사항 수정 */
	@Transactional
	public NoticeDto updateNotice(NoticeDto noticeDto) {
		log.info("[NoticeService] updateNotice Start ===================================");
		log.info("[NoticeService] updateNotice noticeDto : {}", noticeDto);
		
		Notice notice = noticeRepository.findById(noticeDto.getNoticeCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. noticeCode=" + noticeDto.getNoticeCode()));
		
		noticeDto.setNoticeDate(date.format(formatter));
		
		notice.update(noticeDto.getNoticeTitle(),
				noticeDto.getNoticeContent(),
				noticeDto.getNoticeDate()
				);
		
		noticeRepository.save(notice);
		
		log.info("[NoticeService] updateNotice noticeDto : {}", noticeDto);
		
		log.info("[NoticeService] updateNotice End ===================================");
		
		return noticeDto;
	}

	
	/* 5. 공지사항 삭제*/
	@Transactional
	public NoticeDto deleteNotice(Long noticeCode) {
		Notice notice = noticeRepository.findByNoticeCode(noticeCode).orElseThrow(() -> 
				new IllegalArgumentException("해당 공지사항이 존재하지 않습니다. noticeCode=" + noticeCode));
		
		NoticeDto noticeDto = modelMapper.map(notice, NoticeDto.class);
		
		noticeRepository.delete(notice);
		
		return noticeDto;
		
	}

	

	
	
}