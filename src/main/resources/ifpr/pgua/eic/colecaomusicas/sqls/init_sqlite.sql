CREATE TABLE IF NOT EXISTS generos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome varchar(255) NOT NULL,
);

CREATE TABLE IF NOT EXISTS artistas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome varchar(255) NOT NULL,
    contato varchar(255) NOT NULL,
);

CREATE TABLE IF NOT EXISTS musicas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome varchar(255) NOT NULL,
    duracao int NOT NULL,
    anoLancamento int NOT NULL,
    artistaId int NOT NULL,
    generoId int NOT NULL,
    FOREIGN KEY (artistaId) REFERENCES artistas(id),
    FOREIGN KEY (generoId) REFERENCES generos(id),
    
);

CREATE TABLE IF NOT EXISTS playlists (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome varchar(255) NOT NULL,
);

CREATE TABLE IF NOT EXISTS playlistsmusicas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    playlistId int NOT NULL,
    musicaId int NOT NULL,
    FOREIGN KEY (playlistId) REFERENCES playlists(id),
    FOREIGN KEY (musicaId) REFERENCES musicas(id),
);