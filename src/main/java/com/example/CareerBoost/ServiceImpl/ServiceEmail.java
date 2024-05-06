package com.example.CareerBoost.ServiceImpl;


import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class ServiceEmail {

    private final JavaMailSender mailSender;

    public ServiceEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    //hedha ken n7eb nab3eth 3adi

/*@Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("mailtrap@demomailtrap.com");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }*/
    //hedheya ken n7eb n3mel pdf

@Async
public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setFrom("mailtrap@demomailtrap.com");
        helper.setSubject(subject);
        helper.setText(body);

        // Ajout de la pièce jointe
        FileSystemResource file = new FileSystemResource(new File(attachmentPath));
        helper.addAttachment("Certificat.pdf", file);

        mailSender.send(message);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
}

   /* public void sendHtmlEmail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("mailtrap@demomailtrap.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "kbaiermolka@gmail.com");
        message.setSubject("Test email from Spring");

        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }*/
}

