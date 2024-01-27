CREATE TABLE filme_genero (
    filme_id INT NOT NULL REFERENCES filmes(id),
    genero_id INT NOT NULL REFERENCES generos(id),
    PRIMARY KEY (filme_id, genero_id)
);