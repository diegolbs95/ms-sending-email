package com.ms.email.controllers;

import com.ms.email.dto.EmailDto;
import com.ms.email.entity.Email;
import com.ms.email.exceptions.EmailException;
import com.ms.email.service.EmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@Slf4j
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/enviando-email")
    public ResponseEntity<Email> enviandoEmail (@RequestBody @Valid EmailDto emailDto) {
        try {
            var entidade = Email.builder()
                    .emailPara(emailDto.getEmailPara())
                    .emailRef(emailDto.getEmailRef())
                    .propietario(emailDto.getPropietario())
                    .sujeito(emailDto.getSujeito())
                    .texto(emailDto.getTexto())
                    .build();

            emailService.enviaEmail(entidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(entidade);
        }catch (EmailException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Email());
        }
    }
}
