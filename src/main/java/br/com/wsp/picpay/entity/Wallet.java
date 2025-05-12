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

    @Column(name= "email", unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

}
