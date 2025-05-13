package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class TransferNotAuthorizedException extends PicPayException {


    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer not authorized");
        pb.setDetail("The transfer was not authorized by the authorization service");

        return super.toProblemDetail();
    }
}
