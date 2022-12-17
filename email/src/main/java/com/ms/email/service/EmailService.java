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

import static com.ms.email.AppConstant.ERROR_AO_ENVIA_EMAIL;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;
    @Autowired
    private JavaMailSender mailSender;

    public Email enviaEmail(Email entidade) {

        entidade.setDataEnvioEmail(LocalDateTime.now());

        try {
            var message = new SimpleMailMessage();

            message.setFrom(entidade.getEmailRef());
            message.setTo(entidade.getEmailPara());
            message.setSubject(entidade.getSujeito());
            message.setText(entidade.getTexto());
            mailSender.send(message);

            entidade.setStatusEmail(StatusEmail.SUCESSO);
            return repository.save(entidade);
        } catch (Exception e) {
            entidade.setStatusEmail(StatusEmail.ERROR);
            repository.save(entidade);
            throw new EmailException(String.format(ERROR_AO_ENVIA_EMAIL + " causa: %s", e.getMessage()));
        }
    }
}
