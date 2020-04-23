package com.puneet.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puneet.email.config.MyConstants;

@Controller
public class SimpleEmail {
	
	
	@Autowired
	private JavaMailSender emailSender;
	
	
	@ResponseBody
	@RequestMapping("/sendSimpleEmail")
	public String sendSimpleEmail () {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(MyConstants.FRIENDS_EMAIL);
		message.setSubject("Testing Spring Email");
		message.setText("Hi, I am Testing Spring email");
		
		this.emailSender.send(message);
		
		return "Email Sent Successfully";
		
		
	}
	

}
