package com.example.planboardemailsender.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class SmtpTest {

    public static void main(String[] args) {
        // Настройки подключения к SMTP-серверу
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.com"); // Замените на адрес вашего SMTP-сервера
        mailSender.setPort(587); // Замените на порт вашего SMTP-сервера
        mailSender.setUsername("feyleiiia@yandex.ru"); // Замените на ваш SMTP-логин
        mailSender.setPassword("iskesdmrnwfweywj"); // Замените на ваш SMTP-пароль

        // Дополнительные настройки для SMTP
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // Включает вывод логов для отладки

        // Создание тестового письма
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("feyleiiia@yandex.ru"); // Замените на ваш адрес
        message.setTo("feyleiiia@gmail.com"); // Замените на адрес получателя
        message.setSubject("SMTP Test");
        message.setText("This is a test email to verify SMTP connection.");

        // Попытка отправки письма
        try {
            mailSender.send(message);
            System.out.println("Test email sent successfully!");
        } catch (MailException e) {
            System.err.println("Error sending test email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
