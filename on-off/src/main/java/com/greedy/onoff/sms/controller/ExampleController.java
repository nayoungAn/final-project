package com.greedy.onoff.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.onoff.member.dto.MemberDto;
import com.greedy.onoff.sms.dto.SmsDto;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Slf4j
@RestController
@RequestMapping("/ono")
public class ExampleController {
	
	final DefaultMessageService messageService;
	
	public ExampleController() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSHHPHHPSRKKCBO", "8H7I3USEWEPBRE0Y2UGJ1A1ZO9ZUPHHB", "https://api.coolsms.co.kr");
    }
	
	@PostMapping("/sms")
    public MultipleDetailMessageSentResponse sendMany(@RequestBody SmsDto smsDto) {
        ArrayList<Message> messageList = new ArrayList<>();
        log.info(smsDto.toString());
        log.info(smsDto.getMemberList().toString());
        List<MemberDto> memberDto = smsDto.getMemberList();
        
        for (int i = 0; i < memberDto.size(); i++) {
            Message message = new Message();
            message.setFrom("01032885950");
            message.setTo(memberDto.get(i).getMemberPhone());
            message.setText(smsDto.getMsgContent());
            messageList.add(message);
         
            // 메시지 건건 마다 사용자가 원하는 커스텀 값(특정 주문/결제 건의 ID를 넣는등)을 map 형태로 기입하여 전송 후 확인해볼 수 있습니다!
            /*HashMap<String, String> map = new HashMap<>();
            map.put("키 입력", "값 입력");
            message.setCustomFields(map);
            messageList.add(message);*/
        }

        try {
            // send 메소드로 단일 Message 객체를 넣어도 동작합니다!
            // 세 번째 파라미터인 showMessageList 값을 true로 설정할 경우 MultipleDetailMessageSentResponse에서 MessageList를 리턴하게 됩니다!
            MultipleDetailMessageSentResponse response = this.messageService.send(messageList, false, true);

            // 중복 수신번호를 허용하고 싶으실 경우 위 코드 대신 아래코드로 대체해 사용해보세요!
            //MultipleDetailMessageSentResponse response = this.messageService.send(messageList, true);

            System.out.println(response);

            return response;
        } catch (NurigoMessageNotReceivedException exception) {
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

}
