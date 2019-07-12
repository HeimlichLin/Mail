package com.mail.controller;

import com.mail.service.MailService;
import com.mail.service.MailServiceImpl;

/**
 * 發信功能
 */
public class MailController {
	private transient MailService service = new MailServiceImpl();

	public static void main(String[] args) {
		MailController mailController = new MailController();
		mailController.doSendMail();
	}
	
	public void doSendMail() {
		this.service.sendMail();
	}

}
