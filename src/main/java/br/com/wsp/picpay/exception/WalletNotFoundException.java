package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class WalletNotFoundException extends PicPayException {

    private Long id;

    public WalletNotFoundException(Long id) {
        this.id = id;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet not found");
        pb.setDetail("Wallet not found by id: " + id);

        return pb;
    }
}
