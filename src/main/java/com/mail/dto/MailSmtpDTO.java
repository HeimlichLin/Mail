package com.mail.dto;

import java.util.Properties;

public class MailSmtpDTO {
	private final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	public static final MailSmtpDTO INSTANCE = new MailSmtpDTO();
	private Properties props;
	
	public MailSmtpDTO() {
		this.props = System.getProperties();
		this.props.setProperty("mail.smtp.host", "smtp.gmail.com");
		this.props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		this.props.setProperty("mail.smtp.socketFactory.fallback", "false");
		this.props.setProperty("mail.smtp.port", "465");
		this.props.setProperty("mail.smtp.socketFactory.port", "465");
		this.props.setProperty("mail.smtp.starttls.enable", "true");
		this.props.setProperty("mail.smtp.auth", "true");
		this.props.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
	}

	public Properties getProps() {
		return this.props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

}
