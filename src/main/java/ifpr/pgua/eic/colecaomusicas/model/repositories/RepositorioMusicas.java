package ifpr.pgua.eic.colecaomusicas.model.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;

public class RepositorioMusicas {

    private MusicaDAO dao;
    private ArtistaDAO artistaDAO;
    private GeneroDAO generoDAO;

    public RepositorioMusicas(MusicaDAO dao, ArtistaDAO artistaDAO, GeneroDAO generoDAO) {

        this.dao = dao;
        this.artistaDAO = artistaDAO;
        this.generoDAO = generoDAO;
    }

    public Resultado cadastrarMusica(String nome, int duracao, int anoLancamento, Artista artista, Genero genero) {
        if (nome.isBlank() || nome.isEmpty()) {
            return Resultado.erro("Nome inválido!");
        }

        if (duracao < 0) {
            return Resultado.erro("Duração inválida!");
        }

        if (anoLancamento < 0 || anoLancamento > LocalDate.now().getYear()) {
            return Resultado.erro("Ano de Lançamento inválido!");
        }

        Musica musica = new Musica(nome, anoLancamento, duracao, artista, genero);

        return dao.criar(musica);

    }

    private Resultado montaMusica(Musica musica) {
        Resultado r1 = artistaDAO.buscarArtistaMusica(musica.getId());
        if (r1.foiErro()) {
            return r1;
        }
        Artista artista = (Artista) r1.comoSucesso().getObj();
        musica.setArtista(artista);

        // buscar o genero da musica, faremos o mesmo no GeneroDAO
        Resultado r2 = generoDAO.buscarGeneroMusica(musica.getId());
        if (r2.foiErro()) {
            return r2;
        }
        Genero genero = (Genero) r2.comoSucesso().getObj();
        musica.setGenero(genero);

        return Resultado.sucesso("Musica montada", musica);
    }

    public Resultado getById(int musicaId) {

        Resultado r0 = dao.getById(musicaId);

        if (r0.foiSucesso()) {
            Musica musica = (Musica) r0.comoSucesso().getObj();

            return montaMusica(musica);
        }
        return r0;
    }

    public Resultado listar() {

        Resultado resultado = dao.listar();

        if (resultado.foiSucesso()) {
            // iremos finalizar de montar os objetos
            List<Musica> lista = (List<Musica>) resultado.comoSucesso().getObj();

            for (Musica musica : lista) {
                
                Resultado r1 = montaMusica(musica); 
                
                if(r1.foiErro()){
                    return r1;
                }
            }

        }
        return resultado;
    }

    public Resultado listarMusicasPlaylist(int playlistId) {

        Resultado r0 = dao.listarMusicasPlaylist(playlistId);

        if (r0.foiSucesso()) {
            List<Musica> lista = (ArrayList) r0.comoSucesso().getObj();
            for (Musica musica : lista) {
                
                Resultado r1 = montaMusica(musica); 
                
                if(r1.foiErro()){
                    return r1;
                }
            }
        }
        return r0;
    }

}
