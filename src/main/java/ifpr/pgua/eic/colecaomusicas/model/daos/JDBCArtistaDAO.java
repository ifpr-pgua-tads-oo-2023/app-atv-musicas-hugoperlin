package ifpr.pgua.eic.colecaomusicas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Artista;

public class JDBCArtistaDAO implements ArtistaDAO{

    private FabricaConexoes fabrica;

    public JDBCArtistaDAO(FabricaConexoes fabrica){
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Artista artista) {
        //try with resources, para não precisar fechar a conexao
        try(Connection con = fabrica.getConnection()){
            
            //Preparar o comando sql, com o parâmetro para
            //pegar a chave que acabou de ser criada
            PreparedStatement pstm = con.
            prepareStatement("INSERT INTO artistas(nome,contato) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            
            //Ajustar os parâmetros
            pstm.setString(1,artista.getNome());
            pstm.setString(2, artista.getContato());
            
            //Executar o comando
            int ret = pstm.executeUpdate();

            if(ret == 1){
                
                //se conseguiu inserir, vamos pegar o id criado
                ResultSet rs = pstm.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                artista.setId(id);

                return Resultado.sucesso("Artista cadastrado!", artista);
            }
            return Resultado.erro("Erro desconhecido!");
        }catch(SQLException e){
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        
        try (Connection con = fabrica.getConnection()) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM artistas");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Artista> lista = new ArrayList<>();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");

                Artista artista = new Artista(id,nome, contato);
                lista.add(artista);

            }
            
            return Resultado.sucesso("Lista de artistas", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    
    }

    @Override
    public Resultado getById(int id){

        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM artistas WHERE id=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            
            if(rs.next()){
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");

                Artista artista = new Artista(id,nome, contato);

                return Resultado.sucesso("Artista encontrado", artista);
            }else{
                return Resultado.erro("Artista não encontrado!");
            }


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }


    }

    @Override
    public Resultado buscarArtistaMusica(int musicaId) {
        
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT artistaId FROM musicas WHERE id=?");

            pstm.setInt(1, musicaId);

            ResultSet rs = pstm.executeQuery();
            rs.next();

            int artistaId = rs.getInt("artistaId");

            //podemos criar um método no artistaDAO que retorna um artista baseado no id
            return getById(artistaId);


        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }


    }

    @Override
    public Resultado atualizar(int id, Artista novo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado deletar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
    
}
