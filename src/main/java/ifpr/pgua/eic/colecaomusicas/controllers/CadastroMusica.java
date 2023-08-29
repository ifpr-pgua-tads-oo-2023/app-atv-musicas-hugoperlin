package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.entities.Artista;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioArtistas;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioGeneros;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioMusicas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroMusica implements Initializable{

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfAnoLancamento;

    @FXML
    private TextField tfDuracao;

    @FXML
    private ComboBox<Artista> cbArtista;

    @FXML
    private ComboBox<Genero> cbGenero;
    
    private RepositorioMusicas repositorio;
    private RepositorioArtistas repositorioArtistas;
    private RepositorioGeneros repositorioGeneros;

    public CadastroMusica(RepositorioMusicas repositorio, RepositorioGeneros repositorioGeneros, RepositorioArtistas repositorioArtistas){
        this.repositorio = repositorio;
        this.repositorioArtistas = repositorioArtistas;
        this.repositorioGeneros = repositorioGeneros;
    }

    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String sDuracao = tfDuracao.getText();
        String sAnoLancamento = tfAnoLancamento.getText();
        Artista artista = cbArtista.getValue();
        Genero genero = cbGenero.getValue();

        String msg="";

        int duracao=0;
        try{
            duracao = Integer.valueOf(sDuracao);
        }catch(NumberFormatException e){
            msg = "Duração inválida!";
        }

        int ano=0;
        try{
            ano = Integer.valueOf(sAnoLancamento);
        }catch(NumberFormatException e){
            msg = "Ano inválido!";
        }

        Resultado rs = repositorio.cadastrarMusica(nome, duracao, ano, artista, genero);

        Alert alert;
        msg = rs.getMsg();
        if(rs.foiErro()){
            alert = new Alert(AlertType.ERROR,msg);
        }else{
            alert = new Alert(AlertType.INFORMATION,msg);
            
        }

        alert.showAndWait();

    }

    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Resultado r1 = repositorioGeneros.listarGeneros();

        if(r1.foiSucesso()){
            List<Genero> list = (List)r1.comoSucesso().getObj();
            cbGenero.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r1.getMsg());
            alert.showAndWait();
        }

        Resultado r2 = repositorioArtistas.listarArtistas();

        if(r2.foiSucesso()){
            List<Artista> list = (List)r2.comoSucesso().getObj();
            cbArtista.getItems().addAll(list);
        }else{
            Alert alert = new Alert(AlertType.ERROR,r2.getMsg());
            alert.showAndWait();
        }


    }

    

}
