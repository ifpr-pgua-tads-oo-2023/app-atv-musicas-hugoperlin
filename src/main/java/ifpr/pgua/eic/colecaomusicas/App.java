package ifpr.pgua.eic.colecaomusicas;

import ifpr.pgua.eic.colecaomusicas.controllers.CadastroArtista;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroGenero;
import ifpr.pgua.eic.colecaomusicas.controllers.CadastroMusica;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarArtistas;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarGeneros;
import ifpr.pgua.eic.colecaomusicas.controllers.ListarMusicas;
import ifpr.pgua.eic.colecaomusicas.controllers.Principal;
import ifpr.pgua.eic.colecaomusicas.model.daos.ArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.model.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCArtistaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCGeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.JDBCMusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.daos.MusicaDAO;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioArtistas;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioGeneros;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioMusicas;
import io.github.hugoperlin.navigatorfx.BaseAppNavigator;
import io.github.hugoperlin.navigatorfx.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    private ArtistaDAO artistaDAO = new JDBCArtistaDAO(FabricaConexoes.getInstance());
    private RepositorioArtistas repositorioArtistas = new RepositorioArtistas(artistaDAO);
    
    private GeneroDAO generoDAO = new JDBCGeneroDAO(FabricaConexoes.getInstance());
    private RepositorioGeneros repositorioGeneros = new RepositorioGeneros(generoDAO);

    private MusicaDAO musicaDAO = new JDBCMusicaDAO(FabricaConexoes.getInstance());
    private RepositorioMusicas repositorioMusicas = new RepositorioMusicas(musicaDAO, artistaDAO, generoDAO);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }


    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Coleção de Músicas";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "principal.fxml", o->new Principal()));
        registraTela("CADASTROGENERO",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_genero.fxml", 
                      o->new CadastroGenero(repositorioGeneros)
                  )
        );
        registraTela("LISTARGENEROS",
                  new ScreenRegistryFXML(App.class, 
                      "listar_generos.fxml", 
                      o->new ListarGeneros(repositorioGeneros)
                  )
        );
        registraTela("CADASTROARTISTA",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_artista.fxml", 
                      o->new CadastroArtista(repositorioArtistas)
                  )
        );
        registraTela("LISTARARTISTAS",
                  new ScreenRegistryFXML(App.class, 
                      "listar_artistas.fxml", 
                      o->new ListarArtistas(repositorioArtistas)
                  )
        );
        registraTela("CADASTRARMUSICA",
                  new ScreenRegistryFXML(App.class, 
                      "cadastrar_musica.fxml", 
                      o->new CadastroMusica(repositorioMusicas,repositorioGeneros,repositorioArtistas)
                  )
        );
        registraTela("LISTARMUSICAS",
                  new ScreenRegistryFXML(App.class, 
                      "listar_musicas.fxml", 
                      o->new ListarMusicas(repositorioMusicas)
                  )
        );
    }

}