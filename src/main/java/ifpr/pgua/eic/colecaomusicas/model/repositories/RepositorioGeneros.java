package ifpr.pgua.eic.colecaomusicas.model.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.model.daos.FabricaConexoes;
import ifpr.pgua.eic.colecaomusicas.model.daos.GeneroDAO;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;

public class RepositorioGeneros {
    
    private ArrayList<Genero> generos;
    
    private GeneroDAO dao;

    public RepositorioGeneros(GeneroDAO dao){
        generos = new ArrayList<>();
        this.dao = dao;
    }

    public String cadastrarGenero(String nome){
        Genero genero = new Genero(nome);
        Resultado resultado = dao.criar(genero);
        return resultado.getMsg();
    }

    public Resultado listarGeneros(){
        return dao.listar();
    }


}
