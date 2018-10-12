package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.directsend.message.email.DirectSendEmailService;
import com.directsend.message.sms.DirectSendSmsService;
import com.farota.core.email.EmailService;
import com.farota.core.sms.SmsService;

@RunWith(SpringRunner.class)
public class ApplicationTests {
	@Mock
	public EmailService emailService;
	@Mock
	public SmsService smsService;

	@Test
	public void sendMail_Test() throws IOException {
		
		emailService = new DirectSendEmailService();

		String title = "테스트 메일 입니다.";
		String contents = "테스트 메일 내용 입니다.";
		String fromAddress = "junyoung@smarf.kr";
		List<String> listReceiveAddress = new ArrayList<String>();
		listReceiveAddress.add("oecandy@naver.com");
		listReceiveAddress.add("oecandy13@gmail.com");
		
		emailService.sendEmail(title, contents, fromAddress, listReceiveAddress);

	}

	@Test
	public void sendSMS_Test() throws IOException {
		
		smsService = new DirectSendSmsService();
		
		String title = "테스트 문자 입니다.";
		String contents = "테스트 문자 내용 입니다.";
		String fromNumber = "01030229832"; //발신자  07088660910: 파로타 서비스(통합 서비스 이용 증명원), 01030229832 : 핸드폰 인증
		List<String> listReceiveNumber = new ArrayList<String>();
		listReceiveNumber.add("01066984294");
		listReceiveNumber.add("01012345678");
		
		smsService.sendSms(title, contents, fromNumber, listReceiveNumber);

	}

}
