package com.greedy.onoff.classes.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.classes.dto.OpenClassesDto;
import com.greedy.onoff.classes.entity.OpenClasses;
import com.greedy.onoff.classes.repository.ClassesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassesService {
	
	private final ClassesRepository classesRepository;
	private final ModelMapper modelMapper;
	
	public ClassesService(ClassesRepository classesRepository, ModelMapper modelMapper) {
		this.classesRepository = classesRepository;
		this.modelMapper = modelMapper;
	}
	
	
	/* 강의 목록 조회 - 페이징, 상태 여부 'N' 포함 (관리자) */
	public Page<OpenClassesDto> selectClassListForAdmin(int page) {
		
		log.info("[ClassesService] selectClassListForAdmin Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classCode").descending());
		
		Page<OpenClasses> classList = classesRepository.findAll(pageable);
		Page<OpenClassesDto> classDtoList = classList.map(OpenClasses -> modelMapper.map(OpenClasses, OpenClassesDto.class));
		
		
		log.info("[ClassesService] classDtoList : {}", classDtoList.getContent());
		
		log.info("[ClassesService] selectClassListForAdmin End =====================" );
		
		return classDtoList;
	}




	
	/* 강의 목록 조회 - 강의명 검색 기준, 페이징, 상태 여부 'N' 포함 (관리자)  */
	public Page<OpenClassesDto> selectClassListByClassName(int page, String className) {
		
		log.info("[ClassesService] selectClassListByClassName Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("classCode").descending());
		
		Page<OpenClasses> classList = classesRepository.findByClassNameContains(pageable, className);
		Page<OpenClassesDto> classDtoList = classList.map(OpenClasses -> modelMapper.map(OpenClasses, OpenClassesDto.class));
		
		log.info("[ClassesService] classDtoList : {}", classDtoList.getContent());
		
		log.info("[ClassesService] selectClassListByClassName End =====================" );
		
		return classDtoList;
	}



	/* 강의 상세 조회 - classCode로 강의 1개 조회, 상태 여부 'N' 포함 (관리자) */
	public OpenClassesDto selectClassForAdmin(Long classCode) { 
        log.info("[ClassesService] selectClassForAdmin Start ===================================");
        log.info("[ClassesService] classCode : " + classCode);
        
        OpenClasses classes = classesRepository.findByClassCode(classCode)
        		.orElseThrow(() -> new IllegalArgumentException("해당 강의가 없습니다. classCode=" + classCode));
        OpenClassesDto classesDto = modelMapper.map(classes, OpenClassesDto.class);
        
        log.info("[ClassesService] classesDto : " + classesDto);
        
        log.info("[ClassesService] selectClassForAdmin End ===================================");
        
        return classesDto;
	}



	/*  강의 등록 */
	@Transactional
	public OpenClassesDto insertClasses(OpenClassesDto openClassesDto) {
		
		log.info("[ClassesService] insertClasses Start ===================================");
		log.info("[ProductService] insertClasses : {}", openClassesDto);
		
	
			classesRepository.save(modelMapper.map(openClassesDto, OpenClasses.class));
		
		log.info("[ProductService] insertProduct End ===================================");
		
		return openClassesDto;
		
	}
//	
//	/* 8. 상품 수정 */
//	@Transactional
//	public ProductDto updateProduct(ProductDto productDto) {
//
//		log.info("[ProductService] updateProduct Start ===================================");
//		log.info("[ProductService] productDto : {}", productDto);
//
//		String replaceFileName = null;
//
//		try {
//
//			Product oriProduct = productRepository.findById(productDto.getProductCode()).orElseThrow(
//					() -> new IllegalArgumentException("해당 상품이 없습니다. productCode=" + productDto.getProductCode()));
//			String oriImage = oriProduct.getProductImageUrl();
//
//			/* 이미지를 변경하는 경우 */
//			if (productDto.getProductImage() != null) {
//					
//				/* 새로 입력 된 이미지 저장 */
//				String imageName = UUID.randomUUID().toString().replace("-", "");
//				replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productDto.getProductImage());
//				productDto.setProductImageUrl(replaceFileName);
//				
//				/* 기존에 저장 된 이미지 삭제*/
//				FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
//
//			} else { 
//				/* 이미지를 변경하지 않는 경우 */
//				productDto.setProductImageUrl(oriImage);
//			}
//			
//			/* 조회 했던 기존 엔티티의 내용을 수정 */
//			oriProduct.update(productDto.getProductName(), 
//					productDto.getProductPrice(), 
//					productDto.getProductDescription(), 
//					productDto.getProductOrderable(), 
//					modelMapper.map(productDto.getCategory(), Category.class), 
//					productDto.getProductImageUrl(), 
//					productDto.getProductStock());
//			
//			productRepository.save(oriProduct);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//			try {
//				FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
//		
//		log.info("[ProductService] updateProduct End ===================================");
//
//		return productDto;
//	}
//
//
//	
//	
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//


	
}
