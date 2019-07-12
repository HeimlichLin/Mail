package com.mail.service;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.mail.controller.MailBuilder;
import com.mail.dto.MailDTO;
import com.mail.dto.MailMessageDTO;

public class MailServiceImpl implements MailService {
	private Logger logger = Logger.getLogger(MailServiceImpl.class);
	private MailDTO dto = new MailDTO();
	
	@Override
	public void sendMail() {
		this.setMailMessage(this.dto);
		try {
			MailBuilder mailBuilder = new MailBuilder(this.dto);
			mailBuilder.sendMail();
			this.logger.info("Finish");
		} catch (MessagingException e) {
			this.logger.error("error:" + e);
		}
	}
	
	/**
	 * 建立mail內容
	 * @param dto
	 * 這部分可再配合商務邏輯需求，再重構
	 */
	private void setMailMessage(final MailDTO dto) {
		MailMessageDTO mailMessageDTO = new MailMessageDTO();
		mailMessageDTO.setMailBoxID("MAILID");
		mailMessageDTO.setMailBoxPWD("PASSWORD");
		mailMessageDTO.setMailBoxHost("@gamil.com");		
		mailMessageDTO.setMailTo("ZZZ@gamil.com");
//		mailMessageDTO.setMailTo("1@gamil.com");		
//		mailMessageDTO.setMailCc("2@gamil.com");
//		mailMessageDTO.setMailCc("3@gamil.com");
//		mailMessageDTO.setMailBCc("4@gamil.com");
//		mailMessageDTO.setMailBCc("5@gamil.com");
		mailMessageDTO.setFilePath("D:\\前端\\1.txt");// "\"需要跳脫符號
		mailMessageDTO.setFilePath("D:\\前端\\2.txt");// "\"需要跳脫符號		
		mailMessageDTO.setSubject("測試信");
		mailMessageDTO.setContent("測試");		
		dto.setMailMessageDTO(mailMessageDTO);
	}

}
