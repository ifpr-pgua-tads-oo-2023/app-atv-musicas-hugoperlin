use musicas;

CREATE TABLE IF NOT EXISTS generos (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS artistas (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    contato varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS musicas (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    duracao int NOT NULL,
    anoLancamento int NOT NULL,
    artistaId int NOT NULL,
    generoId int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (artistaId) REFERENCES artistas(id),
    FOREIGN KEY (generoId) REFERENCES generos(id),
    
);