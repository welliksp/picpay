package br.com.wsp.picpay.repository;

import br.com.wsp.picpay.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
