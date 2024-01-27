CREATE TABLE filme_genero (
    filme_id BIGINT NOT NULL REFERENCES filmes(id),
    genero_id BIGINT NOT NULL REFERENCES generos(id),
    PRIMARY KEY (filme_id, genero_id)
);