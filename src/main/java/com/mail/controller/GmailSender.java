package com.mail.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 重構前
 */
public class GmailSender {
	
	public static void main(String[] args) throws AddressException,
			MessagingException {
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.starttls.enable", "true");
		props.setProperty("mail.smtp.auth", "true");
		final String username = "MAILID";
		final String password = "PASSWORD";
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(username + "@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("jerry791129@gmail.com", false));

			File f = new File("D:\\前端\\1.txt");// "\"需要跳脫符號
			StringBuffer fileContent = new StringBuffer("");
			BufferedReader txtReader = null;
			String line;
			try {
				if (!f.exists()) {
					System.out.println("沒資料");
				}
				txtReader = new BufferedReader(new InputStreamReader(
						new FileInputStream(f), "BIG5"));
				while ((line = txtReader.readLine()) != null) {
					fileContent.append(line + "\n");
				}
				msg.setSubject("新聞中的電子報");
				msg.setContent(fileContent.toString(), "text/html;charset=BIG5");
				Transport.send(msg);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Message sent.");
		} catch (MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
		}
	}

}