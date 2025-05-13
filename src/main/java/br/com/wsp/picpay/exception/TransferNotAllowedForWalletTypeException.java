package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class TransferNotAllowedForWalletTypeException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(UNPROCESSABLE_ENTITY);

        pb.setTitle("This wallet type does not allow transfers");

        return pb;
    }
}
