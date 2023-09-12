package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioGeneros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroGenero implements Initializable{

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNome;

    @FXML
    private Button btAcao;

    private RepositorioGeneros repositorio;

    private Genero anterior;

    public CadastroGenero(RepositorioGeneros repositorio){
        this.repositorio = repositorio;
    }

    public CadastroGenero(RepositorioGeneros repositorio, Genero anterior){
        this.repositorio = repositorio;
        this.anterior = anterior;
    }


    @FXML
    void cadastrar(ActionEvent event) {
        String nome = tfNome.getText();
        String id = tfId.getText();

        String msg;
        if(anterior == null){
            msg = repositorio.cadastrarGenero(nome);
        }else{
            msg = repositorio.alterarGenero(Integer.valueOf(id),nome);
        }
        
        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();

    }




    @FXML
    void cancelar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(anterior != null){
            tfId.setText(anterior.getId()+"");
            tfNome.setText(anterior.getNome());

            btAcao.setText("Atualizar");
        }
    }

}
