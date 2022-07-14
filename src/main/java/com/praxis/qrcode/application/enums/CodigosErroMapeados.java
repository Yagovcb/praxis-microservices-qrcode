package com.praxis.qrcode.application.enums;

import com.praxis.qrcode.utils.Utils;

public enum CodigosErroMapeados {

    E400000("400",Utils.ZERO, "Requisição mal formatada."),
    E401000("401", Utils.ZERO, "Necessaria a autenticação."),
    E403000("403", Utils.ZERO, "Acesso ao conteudo solicitado negado."),
    E404000("404", Utils.ZERO, "Não foi possivel encontrar o conteudo solicitado."),
    E405000("405", Utils.ZERO, "Método desativado e não pode mais ser acessado."),
    E412000("412", Utils.ZERO, "O servidor não atende as pré-condições enviadas."),
    E412001("412", "001", "Não foi possivel criar o QrCode com os parametros informados."),
    E415000("415", Utils.ZERO, "O formato de mídia dos dados requisitados não é suportado pelo servidor."),
    E500000("500", Utils.ZERO, "Erro interno do servidor. Contate o administrador!"),
    E503000("503", Utils.ZERO, "Serviço indisponivel no momento. \n Servidor pode estar em manutenção ou sobrecarregado. Contate o administrador!");


    private final String errorTypeCode;
    private final String errorcode;
    private final String message;


    CodigosErroMapeados(String errorTypeCode, String errorcode, String message) {
        this.errorTypeCode = errorTypeCode;
        this.errorcode = errorcode;
        this.message = message;
    }

    public String getErrorTypeCode() {
        return errorTypeCode;
    }

    public String getErrorcode() {
        return String.format("%s.%s", errorTypeCode, errorcode);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("Código da falha: %s = %s.", getErrorcode(), getMessage());
    }
}
