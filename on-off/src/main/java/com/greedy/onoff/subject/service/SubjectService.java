package com.greedy.onoff.subject.service;



import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.onoff.subject.dto.SubjectDto;
import com.greedy.onoff.subject.entity.Subject;
import com.greedy.onoff.subject.repository.SubjectRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubjectService {
	
	private final SubjectRepository subjectRepository;
	private final ModelMapper modelMapper;
	
	public SubjectService(SubjectRepository subjectRepository,  ModelMapper modelMapper) {
		this.subjectRepository = subjectRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 1. 과목 목록 조회 - 페이징, 상태 여부 (관리자)*/
	public Page<SubjectDto> selectSubjectListForAdmin(int page) {
		
		log.info("[SubjectService] selectSubjectList Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("subjectCode").descending());
		
		Page<Subject> subjectList = subjectRepository.findAll(pageable);
		Page<SubjectDto> subjectDtoList = subjectList.map(subject -> modelMapper.map(subject, SubjectDto.class));
		
		log.info("[SubjectService] subjectDtoList : {}", subjectDtoList.getContent());
		
		log.info("[SubjectService] subjectDtoList End =====================" );
		
		return subjectDtoList;
	}
	
//	
//	/* 2. 상품 목록 조회 - 페이징, 주문 불가 상품 포함 (관리자) */
//	public Page<ProductDto> selectProductListForAdmin(int page) {
//		
//		log.info("[ProductService] selectProductListForAdmin Start =====================" );
//		
//		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("productCode").descending());
//		
//		Page<Product> productList = productRepository.findAll(pageable);
//		Page<ProductDto> productDtoList = productList.map(product -> modelMapper.map(product, ProductDto.class));
//		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
//		productDtoList.forEach(product -> product.setProductImageUrl(IMAGE_URL + product.getProductImageUrl()));
//		
//		log.info("[ProductService] productDtoList : {}", productDtoList.getContent());
//		
//		log.info("[ProductService] selectProductListForAdmin End =====================" );
//		
//		return productDtoList;
//	}
//	
//	/* 3. 상품 목록 조회 - 카테고리 기준, 페이징, 주문 불가 상품 제외 (고객) */
//	public Page<ProductDto> selectProductListByCategory(int page, CategoryDto category) {
//		
//		log.info("[ProductService] selectProductListByCategory Start =====================" );
//		
//		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("productCode").descending());
//		
//		/* 카테고리 엔티티 조회 */
//		Category findCategory = categoryRepository.findById(category.getCategoryCode())
//				.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. categoryCode = " + category.getCategoryCode()));
//		
//		Page<Product> productList 
//		= productRepository.findByCategoryAndProductOrderable(pageable, findCategory, "Y");
//		Page<ProductDto> productDtoList = productList.map(product -> modelMapper.map(product, ProductDto.class));
//		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
//		productDtoList.forEach(product -> product.setProductImageUrl(IMAGE_URL + product.getProductImageUrl()));
//		
//		log.info("[ProductService] productDtoList : {}", productDtoList.getContent());
//		
//		log.info("[ProductService] selectProductListByCategory End =====================" );
//		
//		return productDtoList;
//	}
//	
//	/* 4. 상품 목록 조회 - 상품명 검색 기준, 페이징, 주문 불가 상품 제외 (고객) */
//	public Page<ProductDto> selectProductListByProductName(int page, String productName) {
//		
//		log.info("[ProductService] selectProductListByProductName Start =====================" );
//		
//		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("productCode").descending());
//		
//		Page<Product> productList = productRepository.findByProductNameContainsAndProductOrderable(pageable, productName, "Y");
//		Page<ProductDto> productDtoList = productList.map(product -> modelMapper.map(product, ProductDto.class));
//		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
//		productDtoList.forEach(product -> product.setProductImageUrl(IMAGE_URL + product.getProductImageUrl()));
//		
//		log.info("[ProductService] productDtoList : {}", productDtoList.getContent());
//		
//		log.info("[ProductService] selectProductListByProductName End =====================" );
//		
//		return productDtoList;
//	}
//
//	
//	/* 5. 상품 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 제외(고객) */
//	public ProductDto selectProduct(Long productCode) {
//		
//		log.info("[ProductService] selectProduct Start =====================" );
//		log.info("[ProductService] productCode : {}", productCode);
//		
//		Product product = productRepository.findByProductCode(productCode)
//				.orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productCode=" + productCode));
//		ProductDto productDto = modelMapper.map(product, ProductDto.class);
//		productDto.setProductImageUrl(IMAGE_URL + productDto.getProductImageUrl()); 
//		
//        log.info("[ProductService] productDto : " + productDto);
//		
//		log.info("[ProductService] selectProduct End =====================" );
//		
//		return productDto;
//	}
//
//	/* 6. 상품 상세 조회 - productCode로 상품 1개 조회, 주문 불가 상품 포함(관리자) */
//	public ProductDto selectProductForAdmin(Long productCode) { 
//        log.info("[ProductService] selectProductForAdmin Start ===================================");
//        log.info("[ProductService] productCode : " + productCode);
//        
//        Product product = productRepository.findById(productCode)
//        		.orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productCode=" + productCode));
//        ProductDto productDto = modelMapper.map(product, ProductDto.class);
//        productDto.setProductImageUrl(IMAGE_URL + productDto.getProductImageUrl());
//        
//        log.info("[ProductService] productDto : " + productDto);
//        
//        log.info("[ProductService] selectProductForAdmin End ===================================");
//        
//        return productDto;
//	}
//
//	/* 7. 상품 등록 */
//	@Transactional
//	public ProductDto insertProduct(ProductDto productDto) {
//		
//		log.info("[ProductService] insertProduct Start ===================================");
//		log.info("[ProductService] productDto : {}", productDto);
//		String imageName = UUID.randomUUID().toString().replace("-", "");
//		String replaceFileName = null;
//		
//		try {
//			replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productDto.getProductImage());
//			productDto.setProductImageUrl(replaceFileName);
//			
//			log.info("[ProductService] replaceFileName : {}", replaceFileName);
//			
//			productRepository.save(modelMapper.map(productDto, Product.class));
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
//		log.info("[ProductService] insertProduct End ===================================");
//		
//		return productDto;
//		
//	}
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
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
