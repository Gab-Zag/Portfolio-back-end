package com.gab.portfolio.service;

import com.gab.portfolio.dto.ContactRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${mail.to}")
    private String mailTo;

    public void sendContactEmail(ContactRequest request) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(mailTo);
            helper.setSubject("[Portfolio] " + request.getSubject());
            helper.setReplyTo(request.getEmail());

            String body = """
                    Nova mensagem do portfólio!
                    
                    Nome:    %s
                    Email:   %s
                    Assunto: %s
                    
                    Mensagem:
                    %s
                    """.formatted(
                    request.getName(),
                    request.getEmail(),
                    request.getSubject(),
                    request.getMessage()
            );

            helper.setText(body, false);
            mailSender.send(message);
            log.info("Email enviado com sucesso de: {}", request.getEmail());

        } catch (Exception e) {
            log.error("Erro ao enviar email: {}", e.getMessage());
            throw new RuntimeException("Falha ao enviar email", e);
        }
    }
}