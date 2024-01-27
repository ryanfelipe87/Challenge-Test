CREATE TABLE filme (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(10) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    sinopse TEXT NOT NULL,
    ano_lancamento INT NOT NULL,
    classificacao_indicativa VARCHAR(5) NOT NULL,
    duracao INT NOT NULL,
    disponibilidade BOOLEAN NOT NULL
);
