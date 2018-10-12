package com.farota.core.email;

import java.io.IOException;
import java.util.List;

public interface EmailService {
	/**
	 * @param String title : 메일의 제목 
	 * @param String contents : 메일의 본문
	 * @param String fromAddress : 발신 E-mail 
	 * @param List listReceiveAddress : 받는 이의 E-mail 입니다. ‘,’로 구분할 수 있습니다.
	 * @return integer statusCode
	 * <br>
	 * 	0   : 정상발송 <br>
		100 : POST validation 실패 <br> 
		101 : 회원정보가 일치하지 않음 <br>
		102 : subject, message 정보가 없습니다. <br>
		103 : sender 이메일이 유효하지 않습니다. <br>
		104 : recipient 이메일이 유효하지 않습니다. <br>
		105 : 본문에 포함하면 안되는 확장자가 있습니다. <br>
		106 : body validation 실패 <br>
		107 : 받는 사람이 없습니다. <br>
		109 : return_url이 없습니다. <br>
		110 : 첨부파일이 없습니다. <br>
		111 : 첨부파일의 개수가 5개를 초과합니다. <br>
		112 : 첨부파일의 Size가 10MB를 넘어갑니다. <br>
		113 : 첨부파일이 다운로드 되지 않았습니다. <br>
		205 : 잔액부족<br>
		999 : Internal Error.<br>
	 * @throws IOException
	 */
	public int sendEmail(String title, String contents, String fromAddress, List<String> listReceiveAddress)
			throws IOException;

}
