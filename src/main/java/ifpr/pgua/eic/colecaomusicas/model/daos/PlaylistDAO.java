package ifpr.pgua.eic.colecaomusicas.model.daos;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;

public interface PlaylistDAO {
    
    Resultado criar(Playlist playlist);
    Resultado listar();
    Resultado calcularTotalMinutosPlaylist(int id);

}
