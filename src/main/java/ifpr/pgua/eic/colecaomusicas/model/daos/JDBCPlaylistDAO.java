package ifpr.pgua.eic.colecaomusicas.model.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;

public class JDBCPlaylistDAO implements PlaylistDAO{

    private static final String INSERT = "INSERT INTO playlists(nome) VALUES (?)";
    private static final String INSERTMUSICA = "INSERT INTO playlistsmusicas(playlistId,musicaId) VALUES (?,?)";
    private static final String SELECT = "SELECT * FROM playlists";
    private static final String CALLTOTALMINUTOS = "CALL total_minutos_playlist(?,?)";

    private FabricaConexoes fabrica;

    public JDBCPlaylistDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }


    @Override
    public Resultado criar(Playlist playlist) {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, playlist.getNome());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int idPlaylist = rs.getInt(1);
                playlist.setId(idPlaylist);

                PreparedStatement pstm2 = con.prepareStatement(INSERTMUSICA);

                for(Musica m:playlist.getMusicas()){
                    pstm2.setInt(1, playlist.getId());
                    pstm2.setInt(2, m.getId());
                    
                    pstm2.executeUpdate();

                }


                return Resultado.sucesso("Playlist cadastrada", playlist);

            }
            return Resultado.erro("Erro desconhecido!");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECT);

            ResultSet rs = pstm.executeQuery();

            List<Playlist> lista = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Playlist playlist = new Playlist(id, nome);

                lista.add(playlist);

            }
            return Resultado.sucesso("Lista carregada", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado calcularTotalMinutosPlaylist(int id){

        try (Connection con = fabrica.getConnection()) {

            CallableStatement call = con.prepareCall(CALLTOTALMINUTOS);

            call.setInt(1, id);

            call.registerOutParameter(2,Types.REAL);

            call.execute();

            double res = call.getDouble(2);

            return Resultado.sucesso("Executado com sucesso", res);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }
    



}
