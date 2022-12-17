package com.ms.email.entity;

import com.ms.email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_email")
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;
    private String propietario;
    private String emailRef;
    private String emailPara;
    private String sujeito;

    @Column(columnDefinition = "TEXT")
    private String texto;
    private LocalDateTime dataEnvioEmail;
    private StatusEmail statusEmail;
}
