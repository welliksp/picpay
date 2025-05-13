package br.com.wsp.picpay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_wallet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;


    public Wallet(String fullName, String cpfCnpj, String email, String password, WalletType walletType) {
        this.fullName = fullName;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.password = password;
        this.walletType = walletType;
    }

    public boolean isTransferAllowedForWalletType() {

        return this.walletType.equals(WalletType.Enum.USER.get());
    }

    public boolean isBalancerEqualOrGreatherThan(BigDecimal value) {

        return this.balance.doubleValue() >= value.doubleValue();
    }

    public void debit(BigDecimal value) {

        this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }
}
