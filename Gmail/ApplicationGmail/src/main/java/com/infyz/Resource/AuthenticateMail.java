package com.infyz.Resource;


import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.*;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
@ApplicationScoped
public class AuthenticateMail {
	public void sendMail(String recepient,String subject,String text,String file) {
		Properties properties =new Properties();
		properties.put("mail.smtp.user", "srinivas.t@xxxxxxxx.com");
		properties.put("mail.smtp.password", "logica@d7");;
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.setProperty("java.net.preferIPv4Stack", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.enable", "false");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		String myAccMail="srinivas.t@xxxxxxxxxxx.com";
		String myPw="XXXXXXXXXXXX";
		Session session=Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccMail,myPw);
			}
		});
		Message message=prepairMessage(session,myAccMail,recepient,subject,text, file);
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
		private static Message prepairMessage(Session session,String myAccMail, String recepient,String subject,String text,String file) {
			try {
				Message massage =new MimeMessage(session);
				massage.setFrom(new InternetAddress(myAccMail));
				String[] recipientList = recepient.split(",");
				InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
				int counter = 0;
				for (String recipient : recipientList) {
				    recipientAddress[counter] = new InternetAddress(recipient.trim());
				    counter++;
				}
				massage.setRecipients(Message.RecipientType.TO, recipientAddress);
				massage.setSubject(subject);
				BodyPart messageBodyPart = new MimeBodyPart(); 
				messageBodyPart.setText(text);
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(new File(file));
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(attachmentPart);
				massage.setContent(multipart);
				return massage;
			}catch(Exception e) {
			    Logger.getLogger(AuthenticateMail.class.getName()).log(Level.SEVERE,null,e);
			}
			return null;
	}
	}
