package com.puneet.email.controller;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puneet.email.config.MyConstants;

@Controller
public class EmailWithAttachment {
	
	@Autowired
	private JavaMailSender emailSender; 
	
	@ResponseBody
	@RequestMapping("/sendAttachmentEmail")
	public String sendAttachmentEmail( ) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
		
		boolean multipart = true;
		
		MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
		
		helper.setTo(MyConstants.FRIENDS_EMAIL);
		helper.setSubject("Testing Spring emails with Attachment");
		
		helper.setText("Hi, Testing Spring emails with Attachment ");
		
		// Provide Location of your attachments
		String path1 = "C:/Users/Puneet/Downloads/original.sql";
		String path2 = "C:/Users/Puneet/Downloads/Blank.txt" ;
		
		// Attachment 1
		FileSystemResource file1 = new FileSystemResource(new File(path1));
        helper.addAttachment("Txt file", file1);
 
        // Attachment 2
        FileSystemResource file2 = new FileSystemResource(new File(path2));
        helper.addAttachment("Readme", file2);
		
        emailSender.send(message);
		
		return "Attachment email sent";
		
		
	}
	
	

}
