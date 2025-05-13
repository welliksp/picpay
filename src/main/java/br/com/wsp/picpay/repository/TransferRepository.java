package br.com.wsp.picpay.repository;

import br.com.wsp.picpay.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
