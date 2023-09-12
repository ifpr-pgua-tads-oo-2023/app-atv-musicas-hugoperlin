package ifpr.pgua.eic.colecaomusicas.model.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;

public class RepositorioPlaylists {

    private PlaylistDAO playlistDao;
    private RepositorioMusicas repositorioMusicas;

    public RepositorioPlaylists(PlaylistDAO playlistDao, RepositorioMusicas repositorioMusicas) {
        this.playlistDao = playlistDao;
        this.repositorioMusicas = repositorioMusicas;
    }

    public Resultado cadastrarPlaylist(String nome, List<Musica> musicas) {
        if (nome.isBlank() || nome.isEmpty()) {
            return Resultado.erro("Nome inválido!");
        }

        if (musicas.size() == 0) {
            return Resultado.erro("Nenhuma música selecionada!");
        }

        Playlist playlist = new Playlist(nome);
        playlist.setMusicas(musicas);

        return playlistDao.criar(playlist);
    }

    public Resultado listar(boolean completo) {
        Resultado resultado = playlistDao.listar();
        if (resultado.foiSucesso()) {
            List<Playlist> lista = (ArrayList) resultado.comoSucesso().getObj();

            if(completo){
                for (Playlist playlist : lista) {

                    Resultado r1 = repositorioMusicas.listarMusicasPlaylist(playlist.getId());
                    if (r1.foiSucesso()) {
                        List<Musica> musicas = (ArrayList) r1.comoSucesso().getObj();

                        playlist.setMusicas(musicas);
                    }
                }
            }

        }
        return resultado;
    }

    public Resultado carregarMusicas(Playlist playlist){
        if(playlist.getMusicas() == null){
            Resultado r1 = repositorioMusicas.listarMusicasPlaylist(playlist.getId());
            if (!r1.foiSucesso()) {
                return r1;
            }else{
                List<Musica> musicas = (ArrayList) r1.comoSucesso().getObj();
                playlist.setMusicas(musicas);        
            }

        }
        return Resultado.sucesso("Músicas carregadas", playlist);
            
    }

}
