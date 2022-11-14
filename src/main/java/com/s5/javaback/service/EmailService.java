package com.s5.javaback.service;


import com.s5.javaback.model.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final SpringTemplateEngine templateEngine;

   public void sendHtmlMessage(EmailRequest email){
       MimeMessage message = javaMailSender.createMimeMessage();
       //Todo falta terminar
   }


}
