package com.ms.email.controllers;

import com.ms.email.exceptions.EmailException;
import com.ms.email.factory.EmailFactory;
import com.ms.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviar-email")
    public void enviandoEmail (@RequestBody String emailPara) {
        try {

            log.info(String.format("[Envio-Email] - Solicitando envio de email para email: %s",
                    emailPara));

            emailService.enviarEmail(EmailFactory.criarEmail(emailPara));

            ResponseEntity.status(HttpStatus.CREATED).body("Sucesso");

        } catch (EmailException e){
            log.info(String.format("[Envio-Email] - Erro ao enviar email para: %s",
                    emailPara));
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Falha no envio");
        }
    }
}
