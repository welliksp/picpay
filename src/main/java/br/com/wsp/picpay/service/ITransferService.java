package br.com.wsp.picpay.service;

import br.com.wsp.picpay.dto.TransferRequest;

public interface ITransferService {

    void save(TransferRequest transferRequest);

}
