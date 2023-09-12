package ifpr.pgua.eic.colecaomusicas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.hugoperlin.results.Resultado;
import com.github.javafaker.Faker;

import ifpr.pgua.eic.colecaomusicas.model.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.model.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCGeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCMusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCPlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.PlaylistDAO;
import ifpr.pgua.eic.colecaomusicas.model.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioMusicas;

public class Testes {
 
    public static void main(String[] args) throws SQLException{
        
        System.out.println(FabricaConexoes.getInstance().getConnection().getMetaData().getDatabaseProductName());
        
        
        /*
        Faker faker = new Faker(Locale.getDefault());
        Random rnd  = new Random();

        ArtistaDAO artistaDAO = new JDBCArtistaDAO(FabricaConexoes.getInstance());
        GeneroDAO generoDAO = new JDBCGeneroDAO(FabricaConexoes.getInstance());
        MusicaDAO musicaoDAO = new JDBCMusicaDAO(FabricaConexoes.getInstance());
        PlaylistDAO playlistDAO = new JDBCPlaylistDAO(FabricaConexoes.getInstance());

        RepositorioMusicas repositorio = new RepositorioMusicas(musicaoDAO, artistaDAO, generoDAO);

        /*
        for(int i=0;i<10;i++){
            Artista artista = new Artista(faker.name().fullName(),faker.internet().emailAddress());
            artistaDAO.criar(artista);
        }

        for(int i=0;i<10;i++){
            Genero genero = new Genero(faker.music().genre());
            generoDAO.criar(genero);
        }
         */
        
        //List<Artista> artistas = (ArrayList)artistaDAO.listar().comoSucesso().getObj();
        //List<Genero> generos = (ArrayList)generoDAO.listar().comoSucesso().getObj();

        /*for(int i=0;i<30;i++){
            Artista artista = artistas.get(rnd.nextInt(artistas.size()));
            Genero genero = generos.get(rnd.nextInt(generos.size()));

            Musica musica = new Musica(faker.dragonBall().character(),2000+rnd.nextInt(24), 1+rnd.nextInt(5), artista, genero);

            Resultado res = musicaoDAO.criar(musica);
            System.out.println(res.getMsg());
        }*/

        /*List<Musica> musicas = (ArrayList)musicaoDAO.listar().comoSucesso().getObj();

        Collections.shuffle(musicas);
        Playlist playlist = new Playlist("Playlist 1");
        playlist.setMusicas(musicas.subList(0, 1+rnd.nextInt(10)));
        
        Resultado res = playlistDAO.criar(playlist);
        System.out.println(res.getMsg());*/

        //Resultado res = playlistDAO.calcularTotalMinutosPlaylist(3);
        //System.out.println(res.getMsg());
        //double valor = (double)res.comoSucesso().getObj();
        //System.out.println("Total:"+valor);

    }


}
