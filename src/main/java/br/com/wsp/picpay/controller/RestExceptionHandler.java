package br.com.wsp.picpay.controller;

import br.com.wsp.picpay.exception.PicPayException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException exception) {

        return exception.toProblemDetail();
    }
}
