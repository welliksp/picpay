CREATE TABLE tb_transfers (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          wallet_sender_id BIGINT NOT NULL,
                          wallet_receiver_id BIGINT NOT NULL,
                          value DECIMAL(19, 2) NOT NULL,
                          CONSTRAINT fk_wallet_sender FOREIGN KEY (wallet_sender_id) REFERENCES tb_wallet(id),
                          CONSTRAINT fk_wallet_receiver FOREIGN KEY (wallet_receiver_id) REFERENCES tb_wallet(id)
);