package br.com.wsp.picpay.dto;

import java.math.BigDecimal;

public record TransferRequest(BigDecimal value, Long payer, Long payee) {

}
