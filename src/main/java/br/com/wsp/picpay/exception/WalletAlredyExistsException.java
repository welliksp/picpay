package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class WalletAlredyExistsException extends PicPayException {


    private String detail;

    public WalletAlredyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet already exists");
        pb.setDetail(detail);

        return pb;
    }
}
