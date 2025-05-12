package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class PicPayException extends RuntimeException {

    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(INTERNAL_SERVER_ERROR);

        pb.setTitle("PicPay Internal Server Error");

        return pb;
    }

}
