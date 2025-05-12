package br.com.wsp.picpay.repository;

import br.com.wsp.picpay.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
