package br.com.wsp.picpay.service.impl;

import br.com.wsp.picpay.dto.TransferRequest;
import br.com.wsp.picpay.entity.Transfer;
import br.com.wsp.picpay.entity.Wallet;
import br.com.wsp.picpay.exception.InsufficientBalanceException;
import br.com.wsp.picpay.exception.TransferNotAllowedForWalletTypeException;
import br.com.wsp.picpay.exception.TransferNotAuthorizedException;
import br.com.wsp.picpay.exception.WalletNotFoundException;
import br.com.wsp.picpay.repository.TransferRepository;
import br.com.wsp.picpay.repository.WalletRepository;
import br.com.wsp.picpay.service.IAuthorizationService;
import br.com.wsp.picpay.service.INotificationService;
import br.com.wsp.picpay.service.ITransferService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Slf4j
@Service
public class TransferService implements ITransferService {

    private final TransferRepository repository;
    private final WalletRepository walletRepository;
    private final IAuthorizationService authorizationService;
    private final INotificationService notificationService;

    public TransferService(TransferRepository repository, WalletRepository walletRepository, AuthorizationService authorizationService, NotificationService notificationService) {
        this.repository = repository;
        this.walletRepository = walletRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional
    @Override
    public void save(TransferRequest transferRequest) {

        log.info("Find sender: {}", transferRequest.payer());
        var sender = walletRepository.findById(transferRequest.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferRequest.payer()));
        log.info("Sender: {}", sender);

        log.info("Find receiver: {}", transferRequest.payee());
        var receiver = walletRepository.findById(transferRequest.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferRequest.payee()));
        log.info("Receiver: {}", receiver);

        validateTransfer(transferRequest, sender);

        sender.debit(transferRequest.value());
        receiver.credit(transferRequest.value());

        var transfer = Transfer.builder()
                .sender(sender)
                .receiver(receiver)
                .value(transferRequest.value())
                .build();

        log.info("Saving Sender: {}", sender);
        walletRepository.save(sender);

        log.info("Saving Receiver: {}", receiver);
        walletRepository.save(receiver);

        log.info("Saving transfer");
        var transferSaved = repository.save(transfer);
        log.info("Transfer saved by id: {}", transferSaved);

        log.info("Send Notification");
        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferSaved));
        log.info("Notification Sent");
    }

    private void validateTransfer(TransferRequest transferRequest, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreatherThan(transferRequest.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferRequest)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
