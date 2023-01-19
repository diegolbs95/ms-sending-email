package com.ms.email.config;

import com.ms.email.controllers.EmailController;
import com.ms.email.dto.EmailDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ms.email.AppConstant.FILA_LEITURA_EMAIL;

@Component
public class EscutaMenssagemFila {

    @Autowired
    private EmailController controller;

    @RabbitListener(queues = FILA_LEITURA_EMAIL)
    public void inicializarLeituraFila(String message){
        var email = EmailDto.builder()
                .emailPara(message)
                .build();

        controller.enviandoEmail(email);
    }
}
