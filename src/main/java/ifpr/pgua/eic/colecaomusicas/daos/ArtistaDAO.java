package ifpr.pgua.eic.colecaomusicas.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.models.Artista;

public interface ArtistaDAO {
    Resultado criar(Artista artista);
    Resultado listar();
    Resultado atualizar(int id, Artista novo);
    Resultado deletar(int id);

}
