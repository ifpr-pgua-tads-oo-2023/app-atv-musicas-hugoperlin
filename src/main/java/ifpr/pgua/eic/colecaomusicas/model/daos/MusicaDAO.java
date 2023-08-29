package ifpr.pgua.eic.colecaomusicas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;

public interface MusicaDAO {
    Resultado criar(Musica musica);
    Resultado listar();
    Resultado atualizar(int id, Musica nova);
    Resultado deletar(int id);
}
