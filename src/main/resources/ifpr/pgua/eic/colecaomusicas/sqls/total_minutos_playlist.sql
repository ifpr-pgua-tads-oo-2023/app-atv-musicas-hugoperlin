DELIMITER $$
CREATE PROCEDURE total_minutos_playlist(IN idPlaylist INTEGER, OUT retorno REAL)
BEGIN

    DECLARE totalminutos REAL DEFAULT 0;
    DECLARE minutosmusica REAL DEFAULT 0;
    DECLARE terminou INTEGER DEFAULT 0;

    DECLARE musica_cursor CURSOR FOR
SELECT duracao FROM playlistsmusicas INNER JOIN musicas ON musicaId=musicas.id WHERE playlistsmusicas.playlistId = idPlaylist;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET terminou = 1;
OPEN musica_cursor;

get_musicas:
    LOOP
        FETCH musica_cursor into minutosmusica;
        IF terminou = 1 THEN
            LEAVE get_musicas;
        END IF;
        SET totalminutos = totalminutos + minutosmusica;

    END LOOP get_musicas;

CLOSE musica_cursor;

SET retorno = totalminutos;

END $$
DELIMITER ;