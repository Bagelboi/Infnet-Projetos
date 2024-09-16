CREATE TABLE Produto (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    fornecedor_id BIGINT NOT NULL
);
