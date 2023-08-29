package ifpr.pgua.eic.colecaomusicas;

import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.model.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCGeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCMusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioMusicas;

public class Testes {
 
    public static void main(String[] args) {
        
        ArtistaDAO artistaDAO = new JDBCArtistaDAO(FabricaConexoes.getInstance());
        GeneroDAO generoDAO = new JDBCGeneroDAO(FabricaConexoes.getInstance());
        MusicaDAO musicaoDAO = new JDBCMusicaDAO(FabricaConexoes.getInstance());

        RepositorioMusicas repositorio = new RepositorioMusicas(musicaoDAO, artistaDAO, generoDAO);

        Resultado resultado = repositorio.listar();
        System.out.println(resultado.getMsg());
        List<Musica> lista = (List)resultado.comoSucesso().getObj();

        for(Musica musica:lista){
            System.out.println(musica.getNome());
            System.out.println(musica.getArtista().getNome());
            System.out.println(musica.getGenero().getNome());
        }


    }


}
