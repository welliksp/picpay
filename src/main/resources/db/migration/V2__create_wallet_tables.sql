CREATE TABLE tb_wallet
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name      VARCHAR(255),
    cpf_cnpj       VARCHAR(255) UNIQUE,
    email          VARCHAR(255) UNIQUE,
    password       VARCHAR(255),
    balance        DECIMAL(19, 2),
    wallet_type_id BIGINT,
    CONSTRAINT fk_wallet_type FOREIGN KEY (wallet_type_id)
        REFERENCES tb_wallet_type (id)
);