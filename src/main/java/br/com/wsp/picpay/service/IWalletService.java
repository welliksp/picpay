package br.com.wsp.picpay.service;

import br.com.wsp.picpay.dto.WalletRequest;
import br.com.wsp.picpay.dto.WalletResponse;

public interface IWalletService {

    WalletResponse save(WalletRequest walletRequest);
}
