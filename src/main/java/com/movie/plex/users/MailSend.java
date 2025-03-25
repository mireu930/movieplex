package com.movie.plex.users;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSend {
	@Autowired
	private JavaMailSenderImpl mailSender;
	private String code;

	public void randomCode() {
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<6;i++) {
			if(Math.random()<0.5) {
				System.out.println(Math.random());
				sb.append((char)((int)(Math.random()*10)+'0'));
			} else {
				sb.append((char)((int)(Math.random()*26)+'A'));
			}
		}
		
		code = sb.toString();
	}
	
	public String joinEmail(String email) {
		randomCode();
		String setFrom = ".com"; // email-config에서 설정한 자신의 이메일 주소를 입력
		String toMail = email;
		String title = "회원가입 인증 메일입니다."; // 이메일 제목
		String content = 
				"홈페이지를 방문해주셔서 감사합니다." + 	// html 형태로 작성!
                "<br><br>" + 
			    "인증 번호는" + code + "입니다." + 
			    "<br>" + 
			    "해당 인증번호를 인증번호 확인란에 입력하여 주세요."; // 이메일 내용 입력
		mailSend(setFrom, toMail, title, content);
		return code;
	}
	
	// 이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) { 
		MimeMessage message = mailSender.createMimeMessage();
		// true 값을 전달하면 multipart 형태의 이메일 전송이 가능. 문자 인코딩 설정도 가능하다.
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			 // true 전달 > html 형태로 전송, 작성하지 않으면 단순 텍스트로 전송.
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public String forgetEmail(String email, String pw) {
		String setForm =".com";
		String toMail = email;
		String title = "비밀번호 메일입니다.";
		String content = 
				"비밀번호는"+pw+"입니다."+
				"<br>"+
				"로그인페이지에서 로그인부탁드립니다.";
		mailSend(setForm, toMail, title, content);
		return pw;
	}
		
	
}
