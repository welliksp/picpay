package br.com.wsp.picpay.exception;

import org.springframework.http.ProblemDetail;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

public class SendNotificationException extends PicPayException {


    private String detail;

    public SendNotificationException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(SERVICE_UNAVAILABLE);

        pb.setTitle("Send Notification Error: Service Unavailable");
        pb.setDetail(detail);

        return pb;
    }
}
