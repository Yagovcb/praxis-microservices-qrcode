package com.praxis.qrcode.infrastructure.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaticQrCodeRequest {

    @NotBlank(message = "Informe o telefone vinculado à conta do cliente. Ex. 5511988199614")
    private String phone;

    @NotBlank(message = "Informe a chave usada no QR Code")
    private String key;

    @Pattern(regexp = "[A-z0-9]{0,25}", message = "Use apenas letras e números sem espaços")
    @Size(max = 25, message = "O identificador da transação deve ter no máximo 25 caracteres")
    private String txId;

    @Size(max = 72, message = "A mensagem não pode ser maior que 72 caracteres")
    private String message;

    private BigDecimal value;

    private String nomeUsuarioFinal;
}
