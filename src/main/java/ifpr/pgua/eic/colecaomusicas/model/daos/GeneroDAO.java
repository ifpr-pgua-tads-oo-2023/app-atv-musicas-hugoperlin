package ifpr.pgua.eic.colecaomusicas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;

public interface GeneroDAO {
    //c
    Resultado criar(Genero genero);

    //r
    Resultado listar();
    Resultado getById(int id);
    Resultado buscarGeneroMusica(int musicaId);
    
    //u
    Resultado atualizar(int id, Genero novo);

    //d
    Resultado delete(int id);
}
