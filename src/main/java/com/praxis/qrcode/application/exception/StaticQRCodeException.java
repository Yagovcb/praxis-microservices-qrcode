package com.praxis.qrcode.application.exception;

import com.praxis.qrcode.application.enums.CodigosErroMapeados;

public class StaticQRCodeException extends ExcecoesMapeadas{

    public StaticQRCodeException(){
        super(CodigosErroMapeados.E412001);
    }
}
