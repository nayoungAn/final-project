package com.greedy.onoff.sms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.classes.dto.ClassesHistoryDto;
import com.greedy.onoff.common.ResponseDto;
import com.greedy.onoff.sms.service.SmsService;

@RestController
@RequestMapping("/ono")
public class SmsController {
	
	private final SmsService smsService;
	
	public SmsController(SmsService smsService) {
		this.smsService = smsService;
	}
	
	/* 문자 전송 대상 조회 */
	@GetMapping("/sms")
	public ResponseEntity<ResponseDto> selectSmsListForAdmin(@RequestParam String search ){
		
		List<ClassesHistoryDto> classesHistory = smsService.selectSmsListForAdmin(search);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "문자 전송 대상 조회 완료!", classesHistory)) ;
		
	}
	
	/* 문자 전송 */
	
//	@PostMapping("/sms")
//	public SingleMessageSentResponse sendMmsByResourcePath() throws IOException{
//		ClassPathResource resource = new ClassPathResource("static/sample.jpg");
//		File file = resource.getFile();
//		String imageId = this.smsService.uploadFile(file, StorageType.MMS, null);
//		
//		SmsCriteria smsCriteria = new SmsCriteria();
//		// 발신번호 및 수신번호는 반드시 01032885950 형태로 입력되어야 합니다.
//		smsCriteria.setFrom("01032885950");
//		smsCriteria.setTo("01032885950");
//		smsCriteria.setText("ONO 문자 전송 테스트");
//		smsCriteria.setImageId(imageId);
//		
//		// 여러 건 메시지 전송일 경우 send many 예제와 동일하게 구성하여 발송할 수 있습니다.
//		SingleMessageSentResponse response = this.smsService.sendOne(new SingleMessageSentResponse(smsCriteria));
//		System.out.println(resource);
//		
//		return response;
//		
//	}
	
	
//	@PostMapping("/sms")
//    public SingleMessageSentResponse sendMmsByResourcePath() throws IOException {
//        ClassPathResource resource = new ClassPathResource("static/sample.jpg");
//        File file = resource.getFile();
//        String imageId = this.messageService.uploadFile(file, StorageType.MMS, null);
//
//        Message message = new Message();
//        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
//        message.setFrom("발신번호 입력");
//        message.setTo("수신번호 입력");
//        message.setText("한글 45자, 영자 90자 이하 입력되면 자동으로 SMS타입의 메시지가 추가됩니다.");
//        message.setImageId(imageId);
//
//        // 여러 건 메시지 발송일 경우 send many 예제와 동일하게 구성하여 발송할 수 있습니다.
//        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        System.out.println(response);
//
//        return response;
//    }

}
