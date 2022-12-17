package com.ms.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDto {

    @NotBlank
    private String propietario;
    @NotBlank
    @Email
    private String emailRef;
    @NotBlank
    @Email
    private String emailPara;
    @NotBlank
    private String sujeito;
    @NotBlank
    private String texto;
}
