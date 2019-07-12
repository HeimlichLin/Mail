package com.mail.dto;

public class MailDTO {
	private MailSmtpDTO mailSmtpDTO;
	private MailMessageDTO mailMessageDTO;
	
	public MailDTO() {
		this.mailSmtpDTO = MailSmtpDTO.INSTANCE;
	}
	
	public MailSmtpDTO getMailSmtpDTO() {
		return mailSmtpDTO;
	}
	
	public void setMailSmtpDTO(MailSmtpDTO mailSmtpDTO) {
		this.mailSmtpDTO = mailSmtpDTO;
	}
	
	public MailMessageDTO getMailMessageDTO() {
		return mailMessageDTO;
	}
	
	public void setMailMessageDTO(MailMessageDTO mailMessageDTO) {
		this.mailMessageDTO = mailMessageDTO;
	}

}
