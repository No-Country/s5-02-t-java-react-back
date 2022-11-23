package com.s5.javaback.service;


import com.s5.javaback.model.entity.User;
import com.s5.javaback.model.request.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;


   public void sendHtmlMessage(EmailRequest email) throws MessagingException {
       MimeMessage message = javaMailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
       Context context = new Context();
       context.setVariables(email.getProperties());
       helper.setFrom(email.getFrom());
       helper.setTo(email.getTo());
       helper.setSubject(email.getSubject());
       String html = templateEngine.process(email.getTemplate(),context);
       helper.setText(html, true);
       log.info("Sending email: {} with html body: {}", email, html);
       javaMailSender.send(message);

   }
    public void sendWelcome(User userCreate) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        EmailRequest email = new EmailRequest();
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("name", userCreate.getName());
        properties.put("subscriptionDate", LocalDate.now().toString());
        Context context = new Context();
        context.setVariables(properties);
        helper.setFrom("mercadopagotest11@gmail.com");
        helper.setTo(userCreate.getEmail());
        helper.setSubject("Bienvenidos");
        String html = templateEngine.process("email.html",context);
        helper.setText(html, true);
        log.info("Sending email: {} with html body: {}", email, html);
        javaMailSender.send(message);
    }


    //Todo obtiene el usuario conectado probar
/*   public User userActualContext(){
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       UserDetails userDetails = null;
       if (principal instanceof UserDetails) {
           userDetails = (UserDetails) principal;
       }
       assert userDetails != null;
       String userName = userDetails.getUsername();
       return userService.findByUsername(userName);
   }
*/


}
