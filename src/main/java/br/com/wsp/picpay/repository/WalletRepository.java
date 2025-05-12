package br.com.wsp.picpay.repository;

import br.com.wsp.picpay.entity.Wallet;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByCpfCnpjOrEmail(@NotNull String cpfCnpj, @Email String email);
}
