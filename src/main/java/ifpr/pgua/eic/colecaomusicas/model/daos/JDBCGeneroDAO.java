package ifpr.pgua.eic.colecaomusicas.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;

public class JDBCGeneroDAO implements GeneroDAO {

    private FabricaConexoes fabrica;

    public JDBCGeneroDAO(FabricaConexoes fabrica) {
        this.fabrica = fabrica;
    }

    @Override
    public Resultado criar(Genero genero) {
        try {

            Connection con = fabrica.getConnection();

            // Preparar o comando sql
            PreparedStatement pstm = con.prepareStatement("INSERT INTO generos(nome) VALUES (?)");
            // Ajustar os parâmetros
            pstm.setString(1, genero.getNome());
            // Executar o comando
            int ret = pstm.executeUpdate();

            con.close();

            if (ret == 1) {
                return Resultado.sucesso("Gênero cadastrado", genero);
            }
            return Resultado.erro("Erro não identificado!");
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado listar() {
        try {
            Connection con = fabrica.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM generos");

            ResultSet rs = pstm.executeQuery();

            ArrayList<Genero> lista = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Genero genero = new Genero(id, nome);
                lista.add(genero);
            }
            rs.close();
            pstm.close();
            con.close();

            return Resultado.sucesso("Gêneros listados!", lista);
        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }

    }

    


    @Override
    public Resultado buscarGeneroMusica(int musicaId) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT generoId FROM musicas WHERE id=?");

            pstm.setInt(1, musicaId);

            ResultSet rs = pstm.executeQuery();
            rs.next();
            int generoId = rs.getInt("generoId");

            return getById(generoId);

        } catch (SQLException e) {  
            return Resultado.erro(e.getMessage());
        }

    }

    @Override
    public Resultado getById(int id) {
        try (Connection con = fabrica.getConnection()) {

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM generos WHERE id=?");

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

            if(rs.next()){
                String nome = rs.getString("nome");

                Genero genero = new Genero(id, nome);
                return Resultado.sucesso("Gênero encontrado!", genero);
            }
            return Resultado.erro("Gênero não encontrado!");

        } catch (SQLException e) {
            return Resultado.erro(e.getMessage());
        }
    }

    @Override
    public Resultado atualizar(int id, Genero novo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Resultado delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
