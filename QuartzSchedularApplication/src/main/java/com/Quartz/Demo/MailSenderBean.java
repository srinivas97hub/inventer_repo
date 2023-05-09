package com.Quartz.Demo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSenderBean {

	public void sendEmail(String sender, String senderUsername, String password, String recever, String subject,
			String body, String senderDomain) { 
		Properties properties =new Properties();
		properties.put("mail.smtp.user", sender);
		properties.put("mail.smtp.password", password);;
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.setProperty("java.net.preferIPv4Stack", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.enable", "false");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		Session session=Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender,password);
			}
		});
			Message message=prepairMessage(session,sender,recever,subject,body);
			try {
				Transport.send(message);
				System.out.println("==========Mail Send Sucessfully===============");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	private static Message prepairMessage(Session session, String myAccMail, String recever, String subject, String body) {
		try {
			 MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(myAccMail));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recever));
	         message.setSubject(subject);
	         message.setText(body);
	 
	      return message;
		}catch(Exception e) {
		   e.printStackTrace();
		}
		return null;
	}

}