package com.example.planboardemailsender.listener;

import com.example.planboardemailsender.dto.EmailSendingDto;
import com.example.planboardemailsender.service.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.planboardemailsender.config.KafkaConsumerConfig.GROUP_ID;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaClassListener {
    private static final String EMAIL_SENDING_TOPIC = "email-sending-task";
    private final EmailSenderService senderService;

    @KafkaListener(topics = EMAIL_SENDING_TOPIC, groupId = GROUP_ID)
    public void listener(EmailSendingDto emailSendingDto){
        log.info("KafkaListener send email with {}, {}, {}", emailSendingDto.getEmail(), emailSendingDto.getTitle(), emailSendingDto.getBody());
        try {
            senderService.sendMail(emailSendingDto);
        } catch (MessagingException e) {
            log.error("Error while sending email to {}: {}", emailSendingDto.getEmail(), e.getMessage(), e);
        }
    }
}
