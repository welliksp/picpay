package br.com.wsp.picpay.dto;

import br.com.wsp.picpay.entity.Wallet;
import br.com.wsp.picpay.entity.WalletType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletRequest(@NotBlank String fullName, @NotBlank String cpfCnpj, @Email String email,
                            @NotBlank String password, @NotNull WalletType.Enum walletType) {


    public Wallet toWallet() {

        return new Wallet(fullName, cpfCnpj, email, password, walletType.get());
    }

}
