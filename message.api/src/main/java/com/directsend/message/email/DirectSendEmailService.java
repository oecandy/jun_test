package com.directsend.message.email;

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

import com.farota.core.email.EmailService;

public class DirectSendEmailService implements EmailService {
	
	private static final String API_KEY = "aeuQ23fREaAawjR";
	private static final String API_URL = "https://directsend.co.kr/index.php/api/v2/mail/utf8";
	private static final String API_USERNAME="smarfco";
	
	private String title;
	private String contents;
	private String fromAddress;
	private String listReceiveNumber;
	private String sender_name = "(주) 스마프";

	@Override
	public int sendEmail(String title, String contents, String fromAddress, List<String> listReceiveAddress)
			throws IOException {
		// TODO Auto-generated method stub
		int responseCode = 999;
		
		try {
			URL obj;
			obj = new URL(API_URL);
			HttpsURLConnection con;
			con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			
			listReceiveNumber = StringUtils.join(listReceiveAddress, ",");
			this.title = title;
			this.contents = contents;
			this.fromAddress = fromAddress;
			
			// String returnURL = "http://domain";

			// int bodytag = 1; // HTML이 기본값 입니다. 메일 내용을 텍스트로 보내실 경우 주석을 해제 해주시기 바랍니다.
			// int open = 1; // open 결과를 받으려면 주석을 해제 해주시기 바랍니다.
			// int click = 1; // click 결과를 받으려면 주석을 해제 해주시기 바랍니다.
			// int check_period = 3; // 트래킹 기간을 지정하며 3 / 7 / 15 / 30 일을 기준으로 지정하여 발송해 주시기 바랍니다. (단, 지정을 하지 않을 경우 결과를
									// 받을 수 없습니다.)
			// 아래와 같이 http://domain 일 경우 http://domain?type=[click | open |
			// reject]&mail_id=[MailID]&email=[Email] 과 같은 형식으로 request를 보내드립니다.
			// String option_return_url = "http://domain/";

			// 예약발송 파라미터 추가
			// String mail_type = "NORMAL"; // NORMAL - 즉시발송 / ONETIME - 1회예약 / WEEKLY - 매주정기예약 / MONTHLY - 매월정기예약 / YEARLY
											// - 매년정기예약
			// String start_reserve_time = "2017-05-17 10:00:00"; // 발송하고자 하는 시간
			// String end_reserve_time = "2017-05-17 10:00:00"; // 발송이 끝나는 시간 1회 예약일 경우 $start_reserve_time =
																// $end_reserve_time
			// WEEKLY | MONTHLY | YEARLY 일 경우에 시작 시간부터 끝나는 시간까지 발송되는 횟수 Ex) type = WEEKLY,
			// start_reserve_time = '2017-05-17 13:00:00', end_reserve_time = '2017-05-24
			// 13:00:00' 이면 remained_count = 2 로 되어야 합니다.
			// int remained_count = 1;

			// String agreement_text = "본메일은 [$NOW_DATE] 기준, 회원님의 수신동의 여부를 확인한 결과 회원님께서 수신동의를 하셨기에 발송되었습니다.";
			// String deny_text = "메일 수신을 원치 않으시면 [$DENY_LINK]를 클릭하세요.\nIf you don't want this type of information or e-mail, please click the [$EN_DENY_LINK]";
			// String sender_info_text = "사업자 등록번호:-- 소재지:ㅇㅇ시(도) ㅇㅇ구(군) ㅇㅇ동 ㅇㅇㅇ번지 TEL:--\nEmail: <a href='mailto:test@directsend.co.kr'>test@directsend.co.kr</a>";
			// String logo_path = "http://logoimage.com/image.png";
			// int logo_state = 1; // logo 사용시 1 / 사용안할 시 0

			// 첨부파일의 URL을 보내면 DirectSend에서 파일을 download 받아 발송처리를 진행합니다. 파일은 개당 10MB 이하로 발송을
			// 해야 하며, 파일의 구분자는 '|(shift+\)'로 사용하며 5개까지만 첨부가 가능합니다.
			// String file_url = "http://domain/test.png|http://domain/test1.png";
			// 첨부파일의 이름을 지정할 수 있도록 합니다. 첨부파일의 이름은 순차적(http://domain/test.png - image.png,
			// http://domain/test1.png - image2.png) 와 같이 적용이 되며, file_name을 지정하지 않은 경우 마지막의
			// 파일의 이름이로 메일에 보여집니다.
			// String file_name = "image.png|image2.png";

			String urlParameters = "subject="
					+ URLEncoder.encode(Base64.encodeBase64String(this.title.getBytes("utf-8")), "UTF-8") + "&body="
					+ URLEncoder.encode(Base64.encodeBase64String(this.contents.getBytes("utf-8")), "UTF-8") + "&sender="
					+ URLEncoder.encode(this.fromAddress, "UTF-8") + "&sender_name="
					+ URLEncoder.encode(Base64.encodeBase64String(sender_name.getBytes("utf-8")), "UTF-8")
					+ "&username=" + URLEncoder.encode(API_USERNAME, "UTF-8") + "&recipients="
					+ URLEncoder.encode(listReceiveNumber, "UTF-8")

					// 예약 관련 파라미터 쥬석 해제
					// + "&mail_type=" + java.net.URLEncoder.encode(mail_type, "UTF-8")
					// + "&start_reserve_time=" + java.net.URLEncoder.encode(start_reserve_time,
					// "UTF-8")
					// + "&end_reserve_time=" + java.net.URLEncoder.encode(end_reserve_time,
					// "UTF-8")
					// + "&remained_count=" + java.net.URLEncoder.encode(remained_count, "UTF-8")

					// 필수 안내문구 관련 파라미터 주석 해제
					// + "&agreement_text=" +
					// java.net.URLEncoder.encode(org.apache.commons.codec.binary.Base64.encodeBase64String(agreement_text.getBytes("utf-8")),
					// "UTF-8")
					// + "&deny_text=" +
					// java.net.URLEncoder.encode(org.apache.commons.codec.binary.Base64.encodeBase64String(deny_text.getBytes("utf-8")),
					// "UTF-8")
					// + "&sender_info_text=" +
					// java.net.URLEncoder.encode(org.apache.commons.codec.binary.Base64.encodeBase64String(sender_info_text.getBytes("utg-8")),
					// "UTF-8")
					// + "&logo_path=" + java.net.URLEncoder.encode(logo_path, "UTF-8")
					// + "&logo_state=" + java.net.URLEncoder.encode(logo_state, "UTF-8")

					// returnURL이 있는 경우 주석해제
					// + "&return_url=" + java.net.URLEncoder.encode(returnURL, "UTF-8")

					// 첨부 파일이 있는 경우 주석 해제
					// + "&file_url=" + java.net.URLEncoder.encode(file_url, "UTF-8")
					// + "&file_name=" + java.net.URLEncoder.encode(file_name, "UTF-8")

					// 발송 결과 측정 항목을 사용할 경우 주석 해제
					// + "&open=" + java.net.URLEncoder.encode(open, "UTF-8")
					// + "&click=" + java.net.URLEncoder.encode(click, "UTF-8")
					// + "&check_period=" + java.net.URLEncoder.encode(check_period, "UTF-8")
					// + "&option_return_url=" + java.net.URLEncoder.encode(option_return_url,
					// "UTF-8")

					// 메일 내용 텍스트로 보내실 경우 주석 해제
					// + "&bodytag=" + java.net.URLEncoder.encode(bodytag, "UTF-8")

					+ "&key=" + URLEncoder.encode(API_KEY, "UTF-8");

			/** 수정하지 마시기 바랍니다. **/

			System.setProperty("jsse.enableSNIExtension", "false");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			System.out.println(responseCode);

			/*
			 * responseCode 가 200 이 아니면 내부에서 문제가 발생한 케이스입니다. directsend 관리자에게 문의해주시기 바랍니다.
			 */

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());

			/*
			 * response의 실패 {"status":101}
			 */

			/*
			 * response 성공 {"status":0} 성공 코드번호.
			 */

			return responseCode;
			
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