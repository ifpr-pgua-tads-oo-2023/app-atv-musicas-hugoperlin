package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.models.Genero;
import ifpr.pgua.eic.colecaomusicas.repositories.RepositorioGeneros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class ListarGeneros implements Initializable{

    @FXML
    private ListView<Genero> lstGeneros;

    private RepositorioGeneros repositorio;

    public ListarGeneros(RepositorioGeneros repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstGeneros.getItems().clear();
        Resultado r = repositorio.listarGeneros();

        if(r.foiSucesso()){
            List<Genero> lista = (List)r.comoSucesso().getObj();
            lstGeneros.getItems().addAll(lista);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r.getMsg());
            alert.showAndWait();
        }
    
    }

}
