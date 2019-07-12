package com.mail.dto;

import java.util.ArrayList;
import java.util.List;

import com.mail.utils.Utils;

public class MailMessageDTO {
	private String mailBoxID;
	private String mailBoxPWD;
	private String mailBoxHost;
	private List<String> mailTo = new ArrayList<String>();
	private List<String> mailCc = new ArrayList<String>();
	private List<String> mailBCc = new ArrayList<String>(); // 密副本
	private List<String> filePath = new ArrayList<String>();
	
	
	private String subject;
	private String content;
	
	public String getMailBoxID() {
		return mailBoxID;
	}

	public void setMailBoxID(String mailBoxID) {
		this.mailBoxID = mailBoxID;
	}

	public String getMailBoxPWD() {
		return mailBoxPWD;
	}

	public void setMailBoxPWD(String mailBoxPWD) {
		this.mailBoxPWD = mailBoxPWD;
	}

	public String getMailBoxHost() {
		return mailBoxHost;
	}

	public void setMailBoxHost(String mailBoxHost) {
		this.mailBoxHost = mailBoxHost;
	}

	public String getMailFrom() {
		return mailBoxID + mailBoxHost;
	}

	public List<String> getMailTo() {
		return mailTo;
	}

	public void setMailTo(String str) {
		this.mailTo.add(str);
	}

	public String[] getMailToArray() {
		String str = Utils.listToString(mailTo);
		return Utils.toArray(str);
	}

	public List<String> getMailCc() {
		return mailCc;
	}

	public void setMailCc(String str) {
		this.mailCc.add(str);
	}

	public String[] getMailCcArray() {
		String str = Utils.listToString(mailCc);
		return Utils.toArray(str);
	}

	public List<String> getMailBCc() {
		return mailBCc;
	}

	public void setMailBCc(String str) {
		this.mailBCc.add(str);
	}

	public String[] getMailBCcArray() {
		String str = Utils.listToString(mailBCc);
		return Utils.toArray(str);
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(String str) {
		this.filePath.add(str);
	}
	
	public String[] getFilePathArray() {
		String str = Utils.listToString(filePath);
		return Utils.toArray(str);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
