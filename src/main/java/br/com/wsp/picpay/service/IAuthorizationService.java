package br.com.wsp.picpay.service;

import br.com.wsp.picpay.dto.TransferRequest;

public interface IAuthorizationService {

    boolean isAuthorized(TransferRequest transferRequest);
}
