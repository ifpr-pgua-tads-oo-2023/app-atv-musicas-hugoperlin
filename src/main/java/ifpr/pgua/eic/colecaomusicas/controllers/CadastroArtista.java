package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioArtistas;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioGeneros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroArtista implements Initializable{

    @FXML
    private TextField tfContato;

    @FXML
    private TextField tfNome;

    private RepositorioArtistas repositorio;

    private Artista antigo;

    public CadastroArtista(RepositorioArtistas repositorio){
        this.repositorio = repositorio;
    }

    public CadastroArtista(RepositorioArtistas repositorio, Artista artista){
        this.repositorio = repositorio;
        this.antigo = artista;
    }


    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String contato = tfContato.getText();
        
        Resultado resultado;
        
        if(antigo == null){
            resultado = repositorio.cadastrarArtista(nome, contato);
        }else{
            resultado = repositorio.atualizarArtista(antigo.getId(),nome,contato);
        }

        
        Alert alert;
        
        if(resultado.foiErro()){
            alert = new Alert(AlertType.ERROR, resultado.getMsg());
        }else{
            alert = new Alert(AlertType.INFORMATION, resultado.getMsg());
        }

        alert.showAndWait();
    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        if(antigo != null ){
            tfNome.setText(antigo.getNome());
            tfContato.setText(antigo.getContato());
        }
    
    }

}
