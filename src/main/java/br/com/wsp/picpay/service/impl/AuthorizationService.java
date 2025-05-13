package br.com.wsp.picpay.service.impl;

import br.com.wsp.picpay.client.AuthorizationClient;
import br.com.wsp.picpay.dto.TransferRequest;
import br.com.wsp.picpay.exception.PicPayException;
import br.com.wsp.picpay.service.IAuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorizationService implements IAuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    @Override
    public boolean isAuthorized(TransferRequest transferRequest) {

        log.info("sending request to validate if it is authorized");
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError())
            throw new PicPayException();

        return response.getStatusCode().is2xxSuccessful() ? true : false;
    }
}
