package com.akpro.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import static com.akpro.util.Constants.*;

import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	public boolean sendMail(String name, String to, String pass) {

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		
		try {
			if(crunchifyEmailValidator(to)) {
				Session session = Session.getDefaultInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(ADMIN_EMAIL_ADDRESS, ADMIN_EMAIL_PASSWORD);
					}
				});
	
				Message msg = new MimeMessage(session);
	
				msg.setFrom(new InternetAddress(ADMIN_EMAIL_ADDRESS));
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
	
				msg.setSubject("Akpro Account Details");
				msg.setText("Hello " + name + "\n\n" + "" + "Your Account Details\n" + "" + "Email Address : " + to + "\n"
						+ "" + "Password : " + pass + ""
	
				);
				msg.setSentDate(new Date());
				Transport.send(msg);
				System.out.println("Message sent.");
				return true;
			}
			return false;
		} catch (MessagingException e) {
			System.out.println("Error, cause: " + e);
			return false;
		}
	}
	
	private boolean crunchifyEmailValidator(String email) {
		boolean isValid = false;
		try {
			InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
			isValid = true;
			
		} catch (AddressException e) {
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		return isValid;
	}
	
}
