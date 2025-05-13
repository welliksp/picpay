package br.com.wsp.picpay.service.impl;

import br.com.wsp.picpay.dto.WalletRequest;
import br.com.wsp.picpay.dto.WalletResponse;
import br.com.wsp.picpay.entity.Wallet;
import br.com.wsp.picpay.exception.WalletAlredyExistsException;
import br.com.wsp.picpay.repository.WalletRepository;
import br.com.wsp.picpay.service.IWalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WalletService implements IWalletService {


    private final WalletRepository repository;

    public WalletService(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public WalletResponse save(WalletRequest walletRequest) {

        log.info("Find wallet if exists");
        repository.findByCpfCnpjOrEmail(walletRequest.cpfCnpj(), walletRequest.email())
                .ifPresent(wallet -> {
                    throw new WalletAlredyExistsException("Wallet already exists with the same CPF/CNPJ or email");
                });

        log.info("Saving wallet");
        Wallet walletSaved = repository.save(walletRequest.toWallet());
        log.info("Wallet saved by id: {}", walletSaved.getId());

        return new WalletResponse(walletSaved.getId(), walletSaved.getFullName(), walletSaved.getCpfCnpj(), walletSaved.getEmail());
    }
}
