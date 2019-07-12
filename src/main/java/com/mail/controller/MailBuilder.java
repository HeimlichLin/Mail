package com.mail.controller;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mail.dto.MailDTO;

public class MailBuilder {
	private static final String CONTENT_STYTLE = "text/html;charset=BIG5";
	private MailDTO dto;
	private Session session;

	public MailBuilder(final MailDTO dto) {
		this.dto = dto;
		this.session = Session.getDefaultInstance(dto.getMailSmtpDTO().getProps(),
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(dto.getMailMessageDTO().getMailBoxID(), dto.getMailMessageDTO().getMailBoxPWD());
					}
				});
		
	}

	/**
	 * 發送
	 * @throws MessagingException
	 */
	public void sendMail() throws MessagingException {
		Message msg = creatMessage();
		Transport.send(msg);
	}

	private Message creatMessage() throws AddressException, MessagingException {
		Message msg = new MimeMessage(this.session);
		// 寄件人
		msg.setFrom(new InternetAddress(this.dto.getMailMessageDTO().getMailFrom()));

		// 收件人
		if (this.dto.getMailMessageDTO().getMailTo() != null && !this.dto.getMailMessageDTO().getMailTo().isEmpty()) {
			InternetAddress[] internetAddress = this.creatInternetAddress(this.dto
					.getMailMessageDTO().getMailToArray());
			msg.setRecipients(Message.RecipientType.TO, internetAddress);
		}

		// 副本
		if (this.dto.getMailMessageDTO().getMailCc() != null && !this.dto.getMailMessageDTO().getMailCc().isEmpty()) {
			InternetAddress[] internetAddress = this.creatInternetAddress(dto
					.getMailMessageDTO().getMailCcArray());
			msg.setRecipients(Message.RecipientType.CC, internetAddress);
		}

		// 密副本
		if (this.dto.getMailMessageDTO().getMailBCc() != null && !this.dto.getMailMessageDTO().getMailBCc().isEmpty()) {
			InternetAddress[] internetAddress = this.creatInternetAddress(this.dto
					.getMailMessageDTO().getMailBCcArray());
			msg.setRecipients(Message.RecipientType.BCC, internetAddress);
		}

		// 設定寄件日期
		msg.setSentDate(new Date());
		
		// 中文標題需指定編碼，以避免亂碼
		msg.setSubject(this.dto.getMailMessageDTO().getSubject());

		// 若無附加檔案,則加入郵件內容後寄送
		String[] fileContent = this.dto.getMailMessageDTO().getFilePathArray();

		if (fileContent == null) {
			// msg.setText(msgText); //純文字
			msg.setContent(this.dto.getMailMessageDTO().getContent(), CONTENT_STYTLE); // HTML+CSS+JAVASCRIPT
		} else if (this.dto.getMailMessageDTO().getFilePath() != null && !this.dto.getMailMessageDTO().getFilePath().isEmpty()) {
			MimeBodyPart[] mbp = new MimeBodyPart[fileContent.length + 1];
			FileDataSource[] fds = new FileDataSource[fileContent.length];
			Multipart mp = new MimeMultipart();

			for (int i = 0; i < fileContent.length + 1; i++) {
				mbp[i] = new MimeBodyPart();
				if (i == 0)
					mbp[i].setText(this.dto.getMailMessageDTO().getContent());
				else {
					// . 設定附加檔案
					// . 附加檔案暫只限於同台機器上之檔案
					// . 附加檔案必須包含完整之實際路徑之字串
					fds[i - 1] = new FileDataSource(fileContent[i - 1]);
					mbp[i].setDataHandler(new DataHandler(fds[i - 1]));
					mbp[i].setFileName(fds[i - 1].getName());
				}
				mp.addBodyPart(mbp[i]);
			}
			// 將郵件內容及附加檔案加入郵件
			msg.setContent(mp);
		}
		return msg;
	}

	private InternetAddress[] creatInternetAddress(String[] str) {
		InternetAddress[] internetAddresses = new InternetAddress[str.length];
		for (int i = 0; i < str.length; i++) {
			try {
				internetAddresses[i] = new InternetAddress(str[i]);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return internetAddresses;
	}

}
