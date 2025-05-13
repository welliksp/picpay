package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class InsufficientBalanceException extends PicPayException {

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient balance");
        pb.setDetail("You cannot transfer a value greater than your balance");

        return pb;
    }
}
