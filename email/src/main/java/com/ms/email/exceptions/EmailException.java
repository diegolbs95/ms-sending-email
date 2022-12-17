package com.ms.email.exceptions;

import static com.ms.email.AppConstant.ERROR_AO_ENVIA_EMAIL;

public class EmailException extends RuntimeException{

    public EmailException (){
        super(ERROR_AO_ENVIA_EMAIL);
    }

    public EmailException (String e){
        super(ERROR_AO_ENVIA_EMAIL);
    }
}
