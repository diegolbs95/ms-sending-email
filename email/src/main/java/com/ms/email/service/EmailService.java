package com.ms.email.service;

import com.ms.email.entity.Email;
import com.ms.email.enums.StatusEmail;
import com.ms.email.exceptions.EmailException;
import com.ms.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ms.email.AppConstant.*;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;
    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(Email entidade) {

        entidade.setDataEnvioEmail(LocalDateTime.now());

        try {
            var message = new SimpleMailMessage();

            message.setFrom(EMAIL_PRINCIPAL);
            message.setTo(entidade.getEmailPara());
            message.setSubject(SUJEITO);
            message.setText(TEXTO);
            mailSender.send(message);

            entidade.setStatusEmail(StatusEmail.SUCESSO);
            repository.save(entidade);

        } catch (Exception e) {

            entidade.setStatusEmail(StatusEmail.ERROR);
            repository.save(entidade);
            throw new EmailException(String.format(ERROR_AO_ENVIA_EMAIL + " causa: %s", e.getMessage()));

        }
    }
}
