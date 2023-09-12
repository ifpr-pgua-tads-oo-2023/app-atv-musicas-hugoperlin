package ifpr.pgua.eic.colecaomusicas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;

public interface MusicaDAO {
    Resultado criar(Musica musica);
    Resultado listarMusicasPlaylist(int idPlaylist);
    Resultado listar();
    Resultado calcularTotalMinutos();
    Resultado getById(int id);
    Resultado atualizar(int id, Musica nova);
    Resultado deletar(int id);
}
