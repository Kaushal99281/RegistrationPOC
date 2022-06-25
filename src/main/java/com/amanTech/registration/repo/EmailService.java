package com.amanTech.registration.repo;

// Importing required classes
import com.amanTech.registration.dto.EmailDetails;

// Interface
public interface EmailService {

	// Method
	// To send a simple email
	String sendSimpleMail(EmailDetails details);

	// Method
	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details);
}