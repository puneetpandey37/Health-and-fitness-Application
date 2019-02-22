package com.supafit.common.notification;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
import com.supafit.dao.coach.model.Coach;
import com.supafit.dao.user.model.User;

public class EmailNotification {

	private static String _host = "smtp.gmail.com";
//	private static int _port = 587;
	private static String _login = "supafitapp@gmail.com";
	private static String _pwd = "octlesupafit";
	private MimeMessage message;
	private Session session;
	private User user;
	private Coach coach;
	
	public EmailNotification(User user, Coach coach) throws AddressException, MessagingException {
		Properties props = System.getProperties();
		props.setProperty("mail.debug", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.host", _host);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "587");
		String machineName = "supafit";
		props.setProperty("mail.smtp.localhost", machineName);
		session = Session.getInstance(props, null);
		message = new MimeMessage(session);
		message.setFrom(new InternetAddress("supafitapp@gmail.com"));
		String mailer = "smtpsend";
		message.setHeader("Content-Type", "text/html; charset=utf-8");
		message.setHeader("X-Mailer", mailer);
		message.setSentDate(new Date());
		this.user = user;
		this.coach = coach;
	}

	public void sendCreateAccountMail() throws Exception {
		String receipientEmailId = user.getEmail();
		String subject = "Welcome to Supafit!";
		String content = getContent();
		sendEmail(receipientEmailId, subject, content);
	}
	
	public void sendLocalSignupMail() throws Exception {
		String receipientEmailId = null;
		if(user != null) {
			receipientEmailId = user.getEmail();
		} else {
			receipientEmailId = coach.getEmail();
		}
		String subject = "Welcome to Supafit!";
		String content = getContent();
		sendEmail(receipientEmailId, subject, content);
	}
	
	private void sendEmail(String receipientEmailId, String subject, String content) throws Exception {

		String prot = "smtp";
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				receipientEmailId));
		message.setSubject(subject);
		message.setContent(content, "text/html; charset=utf-8");
		SMTPTransport t = (SMTPTransport) session.getTransport(prot);
		try {
			t.connect(_host, _login, _pwd);
			t.sendMessage(message, message.getAllRecipients());
		} catch (Exception ex) {
			System.out.println("exception sending" + ex.getMessage());
		} finally {
			t.close();
		}
	}
	
	private String getContent() {
		String content = null;
		String name = "";
		if(coach != null) 
			name = coach.getName();
		else if(user != null)
			name = user.getName();
		
//		if("social".equalsIgnoreCase(type))
			content = "<html><head><title>Supafit User Account</title><meta name='viewport' content='initial-scale=1'></head>"
				+ "<body>" + "Dear" + " "
				+ name
				+ ","
				+ "<br>"
				+ "<br>"
				+ "Welcome to Supafit! We congratulate you on your first step towards your Super Fitness!"
				+ "<br>"
				+ "<br>"
				+ "We are excited to be a part of your journey."
				+ "<br>"
				+ "<br>"
				+ "Hope to see you daily!"
				+ "<br>"
				+ "<br>"
				+ "Thanks," + "<br>" + "Team Supafit!" + "<tbody>";
		return content;
	}
}