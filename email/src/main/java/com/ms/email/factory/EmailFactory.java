package com.ms.email.factory;

import com.ms.email.entity.Email;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailFactory {

    public static Email criarEmail (String email){
        return Email.builder()
                .emailPara(email)
                .build();
    }
}
