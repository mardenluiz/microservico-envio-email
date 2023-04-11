package com.ms.sendemail.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ms.sendemail.enums.StatusEmail;
import com.ms.sendemail.model.EmailModel;
import com.ms.sendemail.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private JavaMailSender emailSender;

	@SuppressWarnings("finally")
	public EmailModel sendingEmail(EmailModel emailModel) {
		
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
			
		} catch(MailException e) {
			System.out.println(e.getMessage());
			emailModel.setStatusEmail(StatusEmail.ERROR);
		} finally {
			return emailRepository.save(emailModel);
		}
		
	}

}





