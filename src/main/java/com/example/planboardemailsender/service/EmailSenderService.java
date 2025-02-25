package com.example.planboardemailsender.service;

import com.example.planboardemailsender.dto.EmailSendingDto;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    String username;

    public void sendMail(EmailSendingDto emailSendingDto) throws MessagingException {
        String htmlBody = createHtmlBody(emailSendingDto.getTitle(), emailSendingDto.getBody());
        sendMail(emailSendingDto.getEmail(), emailSendingDto.getTitle(), htmlBody);
    }

    private void sendMail(String address, String title, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setContent(body, "text/html; charset=utf-8");
        message.setSubject(title);
        message.setFrom(username);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));

        mailSender.send(message);
        log.info("Send email  to {}", address);
    }

    private String createHtmlBody(String title, String bodyContent) {
        return "<div style='background: linear-gradient(to right, #ff7eb3, #ff1f6d, #ffd700); padding: 20px; border-radius: 10px; font-family: Arial, sans-serif; text-align: center;'>"
                + "<h2 style='color: #ffffff; font-size: 24px;'>Welcome to Task Tracker!</h2>"
                + "<div style='background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);'>"
                + "<p>" + bodyContent + "We hope you enjoy using our service and find it helpful for your tasks." + "</p>"
                + "</div>"
                + "</div>";
    }
}
