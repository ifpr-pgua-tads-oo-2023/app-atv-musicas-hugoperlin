package ifpr.pgua.eic.colecaomusicas.model.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;

public class JDBCMusicaDAO implements MusicaDAO{
    private static final String INSERTSQL = "INSERT INTO musicas(nome,duracao,anoLancamento,artistaId,generoId) VALUES (?,?,?,?,?)";
    private static final String SELECTSQL = "SELECT * FROM musicas";
    private static final String SELECTIDSQL = "SELECT * FROM musicas WHERE id=?";
    
    private static final String SELECTMUSICAID = "SELECT musicaId FROM playlistsmusicas WHERE playlistId=?";
    private static final String CALLTOTALMINUTOS = "CALL total_minutos(?)";

    private FabricaConexoes fabrica;

    public JDBCMusicaDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Musica musica) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement(INSERTSQL, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, musica.getNome());
            pstm.setInt(2, musica.getDuracao());
            pstm.setInt(3, musica.getAnoLancamento());
            pstm.setInt(4, musica.getArtista().getId());
            pstm.setInt(5, musica.getGenero().getId());

            int ret = pstm.executeUpdate();

            if(ret == 1){
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                musica.setId(id);

                return Resultado.sucesso("Música cadastrada", musica);
            }
            return Resultado.erro("Erro desconhecido!");


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado getById(int id){
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTIDSQL);

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();


            if(rs.next()){
                String nome = rs.getString("nome");
                int duracao = rs.getInt("duracao");
                int anoLancamento = rs.getInt("anoLancamento");

                //iremos buscar artista e genero através do repositório
                Musica musica = new Musica(id,nome, anoLancamento, duracao, null, null);

                return Resultado.sucesso("Musicas listadas", musica);
            }else{
                return Resultado.erro("Musicas não encontrada!");
                
            }
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    
    }

    @Override
    public Resultado listarMusicasPlaylist(int playlistId){

        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTMUSICAID);

            pstm.setInt(1, playlistId);

            ResultSet rs = pstm.executeQuery();

            List<Musica> musicas = new ArrayList<>();

            while(rs.next()){
                int musicaId = rs.getInt("musicaId");

                Resultado r0 = getById(musicaId);

                if(r0.foiSucesso()){
                    Musica musica = (Musica)r0.comoSucesso().getObj();
                    musicas.add(musica);
                }else{
                    return r0;
                }
            }
            return Resultado.sucesso("Musicas recuperadas", musicas);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }



    }

    @Override
    public Resultado listar() {
        
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement(SELECTSQL);

            ResultSet rs = pstm.executeQuery();

            ArrayList<Musica> lista = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int duracao = rs.getInt("duracao");
                int anoLancamento = rs.getInt("anoLancamento");

                //iremos buscar artista e genero através do repositório
                Musica musica = new Musica(id,nome, anoLancamento, duracao, null, null);

                lista.add(musica);
            }

            return Resultado.sucesso("Musicas listadas", lista);

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    
    }

    @Override
    public Resultado calcularTotalMinutos(){

        try (Connection con = fabrica.getConnection()) {

            CallableStatement call = con.prepareCall(CALLTOTALMINUTOS);

            call.registerOutParameter(1,Types.REAL);

            call.execute();

            double res = call.getDouble(1);

            return Resultado.sucesso("Executado com sucesso", res);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado atualizar(int id, Musica nova) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    

}
