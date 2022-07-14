package com.praxis.qrcode.application.exception;

import com.praxis.qrcode.application.enums.CodigosErroMapeados;

public abstract class ExcecoesMapeadas extends Exception {

    protected CodigosErroMapeados codigosErroMapeados;

    protected ExcecoesMapeadas(CodigosErroMapeados codigosErroMapeados){
        super(codigosErroMapeados.getMessage());
        this.codigosErroMapeados = codigosErroMapeados;
    }


    public CodigosErroMapeados getCodigosErroMapeados() {
        return codigosErroMapeados;
    }
}
