package com.amanTech.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

	// Class data members
	private String recipient;
	private String msgBody = "Click here to verify your email";
	private String subject = "Verification E-mail";
	private String attachment="C:/Users/aadar/Pictures/Camera Roll/pic.jpg";
	private String token;

	public EmailDetails(String recipient,String token) {
		this.recipient = recipient;
		this.token=token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getMsgBody() {
		// TODO Auto-generated method stub
		return msgBody;
	}

	public String getSubject() {
		// TODO Auto-generated method stub
		return subject;
	}
	
	

	public String getAttachment() {
		// TODO Auto-generated method stub
		return attachment;
	}

}
