package br.com.wsp.picpay.service.impl;

import br.com.wsp.picpay.dto.WalletRequest;
import br.com.wsp.picpay.dto.WalletResponse;
import br.com.wsp.picpay.entity.Wallet;
import br.com.wsp.picpay.repository.WalletRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @InjectMocks
    WalletService service;

    @Mock
    WalletRepository repository;

    @Mock
    Wallet wallet;
    @Mock
    WalletRequest walletRequest;

    @Mock
    WalletResponse walletResponse;

    @Test
    @DisplayName("Test Create Wallet Should Return Wallet Created")
    void testCreateWallet__shouldReturnWalletCreated() {

        doReturn(wallet).when(repository).save(any());

        WalletResponse result = service.save(walletRequest);

        assertNotNull(result);

        verify(repository, times(1)).save(any());
    }
}