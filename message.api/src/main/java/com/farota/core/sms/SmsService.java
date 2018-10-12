package com.farota.core.sms;

import java.io.IOException;
import java.util.List;

public interface SmsService {

	/**
	 * @param String title : MMS/LMS 발송시에 사용하는 제목 (최대 20자)
	 * @param String contents : 받을 문자 내용 최대 2000바이트.
	 * @param String fromNumber : 발신자  07088660910: 파로타 서비스(통합 서비스 이용 증명원), 01030229832 : 핸드폰 인증
	 * @param List listReceiveNumber : 발송 할 고객 번호 , 로 구분함. ex) 01012341234,0101555123,010303040123
	 * @return integer statusCode
	 * <br>
	 * 	0   : 정상발송
		100 : POST validation 실패<br>
		101 : sender 유효한 번호가 아님<br>
		102 : recipient 유효한 번호가 아님<br>
		103 : api key or user is invalid<br>
		104 : recipient count = 0<br>
		105 : message length = 0, message length >= 2000, title length >= 20<br>
		106 : message validation 실패<br>
		107 : 이미지 업로드 실패<br>
	    108 : 이미지 갯수 초과<br>
	    109 : return_url이 없음<br>
	    110 : 이미지 용량 300kb 초과<br>
	    111 : 이미지 확장자 오류<br>
		205 : 잔액부족<br>
		999 : Internal Error.<br>
	 * @throws IOException
	 */
	public int sendSms(String title, String contents, String fromNumber, List<String> listReceiveNumber)
			throws IOException;

}
