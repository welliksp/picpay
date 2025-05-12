package br.com.wsp.picpay.service.impl;

import br.com.wsp.picpay.dto.WalletRequest;
import br.com.wsp.picpay.dto.WalletResponse;
import br.com.wsp.picpay.entity.Wallet;
import br.com.wsp.picpay.repository.WalletRepository;
import br.com.wsp.picpay.service.IWalletService;
import org.springframework.stereotype.Service;

@Service
public class WalletService implements IWalletService {


    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public WalletResponse save(WalletRequest walletRequest) {

        repository.findByCpfCnpjOrEmail(walletRequest.cpfCnpj(), walletRequest.email())
                .ifPresent(wallet -> {
                    throw new IllegalArgumentException("Wallet already exists with the same CPF/CNPJ or email");
                });

        Wallet walletSaved = repository.save(walletRequest.toWallet());

        return new WalletResponse(walletSaved.getId(), walletSaved.getFullName(), walletSaved.getCpfCnpj(), walletSaved.getEmail());
    }
}
