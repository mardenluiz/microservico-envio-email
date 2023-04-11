package com.ms.sendemail.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.sendemail.dto.EmailDto;
import com.ms.sendemail.model.EmailModel;
import com.ms.sendemail.service.EmailService;

import jakarta.validation.Valid;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;

	Logger logger = LogManager.getLogger(EmailController.class);
	
	@PostMapping("/sending-email")
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {

		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendingEmail(emailModel);

		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
		
	}
}
