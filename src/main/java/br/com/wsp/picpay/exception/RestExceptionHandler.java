package br.com.wsp.picpay.exception;

import br.com.wsp.picpay.dto.InvalidParam;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(PicPayException.class)
    public ProblemDetail handlePicPayException(PicPayException exception) {

        return exception.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        var fieldErrors = exception.getFieldErrors()
                .stream()
                .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
                .toList();

        var pb = ProblemDetail.forStatus(BAD_REQUEST);

        pb.setTitle("Your request parameters didn't validate.");
        pb.setProperty("invalidParams", fieldErrors);

        return pb;
    }
}
