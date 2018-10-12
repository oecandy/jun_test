package com.directsend.message.sms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.farota.core.email.SmsService;

@Service
public class DirectSendSmsService implements SmsService {
	
	private static final String API_KEY = "tKfW3mH4jOGMXbX";
	private static final String API_URL = "https://directsend.co.kr/index.php/api/v1/sms";
	private static final String API_USERNAME="smarfco";
	
	private String title;
	private String contents;
	private String fromNumber;
	private String listReceiveNumber;

	@Override
	public int sendSms(String title, String contents, String fromNumber, List<String> listReceiveNumber)
	//fromNumber 07088660910: 파로타 서비스(통합 서비스 이용 증명원), 01030229832 : 핸드폰 인증
			throws IOException {

		int responseCode = 999;
		
		try {
			// returnURL이 있을 경우 사용하는 URL
			//String url = "https://directsend.co.kr/index.php/api/result_v1/sms";
			URL obj;
			obj = new URL(API_URL);
			HttpsURLConnection con;
			con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			
			/*
			 * returnURL : 실제 발송결과를 return 받을 URL
			 * attaches : 첨부파일의 URL(영문) (jpg, jpeg 파일만 가능, 파일당 300kb 제한)
			 * 각 번호가 유효하지 않을 경우에는 발송이 되지 않습니다.
			*/ 
			
			
			/* 여기서부터 수정해주시기 바랍니다. */
			this.title = title;
			this.contents = contents;
			this.fromNumber = fromNumber;
			this.listReceiveNumber = StringUtils.join(listReceiveNumber, ",");

			// 실제 발송성공실패 여부를 받기 원하실 경우 주석을 해제하신 후 결과를 받을 URL을 입력해 주시기 바랍니다.
			//String returnURL = "http://domain";

			// 첨부파일이 있을 시 아래 주석을 해제하고 첨부하실 파일의 URL을 배열로 입력하여 주시기 바랍니다.
			// jpg파일당 300kb 제한 3개까지 가능합니다.
			//String[] attaches = new String[] {"https://directsend.co.kr/jpgimg1.jpg", "https://directsend.co.kr/jpgimg2.jpg", "https://directsend.co.kr/jpgimg3.jpg"};

			String urlParameters = "title=" + URLEncoder.encode(Base64.encodeBase64String(this.title.getBytes("euc-kr")), "EUC_KR")
					+ "&message=" + URLEncoder.encode(Base64.encodeBase64String(this.contents.getBytes("euc-kr")), "EUC_KR")
					+ "&sender=" + URLEncoder.encode(this.fromNumber, "EUC_KR")
					+ "&username=" + URLEncoder.encode(API_USERNAME, "EUC_KR")
					+ "&recipients=" + URLEncoder.encode(this.listReceiveNumber, "EUC_KR")
					//+ "&return_url=" + java.net.URLEncoder.encode(returnURL, "EUC_KR") // returnURL이 있는 경우 주석해제 바랍니다.
					+ "&key=" + URLEncoder.encode(API_KEY, "EUC_KR")
					+ "&type=" + URLEncoder.encode("java", "EUC_KR");
					//+ "&attaches=" + java.net.URLEncoder.encode(attaches, "EUC_KR");	// 첨부파일이 있는 경우 주석해제 바랍니다.

			System.setProperty("jsse.enableSNIExtension", "false") ; 
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
	 
			responseCode = con.getResponseCode();
			System.out.println(responseCode);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response.toString());
			
			/* 
			 * response의 실패
			 *  {"status":101} 
			 */
			
			/* 
			 * response 성공
			 * {"status":0}
			 *  성공 코드번호.
			 */
			
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
			responseCode = 999;
		} catch(RuntimeException e) {
			e.printStackTrace();
			responseCode = 999;
		}
		return responseCode;
	}
}